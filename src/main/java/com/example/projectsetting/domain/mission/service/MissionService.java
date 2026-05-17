package com.example.projectsetting.domain.mission.service;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.MemberRepository;
import com.example.projectsetting.domain.mission.controller.MissionController;
import com.example.projectsetting.domain.mission.converter.MissionConverter;
import com.example.projectsetting.domain.mission.dto.MissionReqDTO;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.entity.Store;
import com.example.projectsetting.domain.mission.entity.mapping.MemberMission;
import com.example.projectsetting.domain.mission.enums.Status;
import com.example.projectsetting.domain.mission.exception.MissionException;
import com.example.projectsetting.domain.mission.exception.StoreException;
import com.example.projectsetting.domain.mission.exception.code.MissionErrorCode;
import com.example.projectsetting.domain.mission.exception.code.StoreErrorCode;
import com.example.projectsetting.domain.mission.repository.MemberMissionRepository;
import com.example.projectsetting.domain.mission.repository.MissionRepository;
import com.example.projectsetting.domain.mission.repository.StoreRepository;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    // 가게 미션 생성
    @Transactional
    public Void creeteMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        //가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        //미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    //가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ){

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0,pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        //커서가 있는 경우
        if(!cursor.equals("-1")){

            //커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":

                    //커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    //가게 내 미션들 조회 & where 절에 커서값 기입
                    missionList = missionRepository.findMissionByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }

        }else{
            //커서 없이 조회
            missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        List<Mission> content = missionList.getContent();
        if(content.isEmpty()){
            nextCursor = "-1";
        }else{
            Mission lastMission = content.get(content.size() -1);
            nextCursor = lastMission.getId() + ":" + lastMission.getId();
        }

        //미션들 dto로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }


    //미션 목록 조회
    public List<MissionResDTO.Mission> getMissions(
            Long userId,
            String authorization,
            Status status){


        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        List<Mission> missions = missionRepository.findMissionByMemberIdAndStatus(
                status.name(),
                member.getId()
        );

        List<MissionResDTO.Mission> result = new ArrayList<>();

        for(Mission mission : missions){
            MissionResDTO.Mission missionDto
                    = MissionConverter.toResultMission(mission);
            result.add(missionDto);
        }

        return result;
    }

    //미션 성공 누르기
    public ApiResponse<MissionResDTO.Success> success(BaseSuccessCode code, MissionReqDTO.Success dto, String authorization, Long missionId) {

        return null;
    }

    //진행중인 미션 조회
    public MissionResDTO.OffsetPagination<MissionResDTO.Mission> getInProgressMissions(
            MissionReqDTO.InProgress dto,
            Integer pageSize,
            Integer pageNumber,
            String sort) {

        //정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("id").descending();
        }

        //페이지 정보를 PageRequest로ㅗ 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, sortInfo);

        //진행중인 미션 조회
        Page<MemberMission> memberMissionList = memberMissionRepository.findAllByMember_IdAndStatusOrderByIdDesc(dto.userId(), Status.IN_PROGRESS, pageRequest);

        //미션들 응답 DTO로 포장하기
        return MissionConverter.toOffsetPagination(
                memberMissionList
                        .map(memberMission -> MissionConverter.toGetInProgressMission(memberMission.getMission()))
                        .toList(),
                memberMissionList.getNumber(),
                memberMissionList.getSize()
        );
    }






}
