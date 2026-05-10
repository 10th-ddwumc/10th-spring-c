package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.global.pagination.PaginationConverter;
import com.example.umc10th.global.pagination.PaginationResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    private MissionConverter missionConverter;

    //완료/미완료 미션 조회
    public PaginationResDTO.OffsetPagination<MissionResDTO.GetMissions> getMissions(
            Boolean isSuccess,
            MissionReqDTO.getMyMissions dto,
            Integer pageSize,
            Integer pageNumber,
            String  sort) {

        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> result = memberMissionRepository.findAllByMemberAndIsSuccess(member.getId(), isSuccess, pageable);

        return PaginationConverter.toOffsetPagination(
                result.map(MissionConverter::toGetMissions).toList(),
                result.getNumber(),
                result.getSize()
        );
    }

    //미션 성공 누르기
    public MissionResDTO.Success success(Long missionId, Long memberId) {
        return null;
    }

    //가게 미션 생성
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);

        return null;
    }

    //가게 내 미션 조회
    public PaginationResDTO.CursorPagination<MissionResDTO.GetMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        //커서가 있는 경우
        if (!cursor.equals("-1")) {

            //커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id" :

                    //커서 타입 변솬
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    //가게 내 미션들 조회 & where절에 커서값 기입
                    missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }

        }

        //커서 없이 조회
        missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);

        nextCursor = missionList.getContent()
                .get(missionList.getContent().size() - 1)
                .getId()
                + ":" +
                missionList.getContent()
                        .get(missionList.getContent().size() - 1)
                        .getId();

        return PaginationConverter.toCursorPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}
