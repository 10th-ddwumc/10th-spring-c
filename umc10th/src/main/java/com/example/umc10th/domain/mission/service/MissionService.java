package com.example.umc10th.domain.mission.service;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDto.MissionList getMissionList(Member member, MissionReqDto.GetMissionList request) {
        //페이지 번호/사이즈 결정
        PageRequest pageRequest=PageRequest.of(0, request.size()!=null?request.size():10);

        //특정 멤버의 특정 상태 미션 목록 조회
        Page<MemberMission> missionPage=memberMissionRepository.findAllByMemberAndStatus(
                member,
                request.status().toString(),
                request.lastId(),
                pageRequest
        );

        //엔티티 -> Dto
        return MissionConverter.toMemberMissionListDto(missionPage);
    }

    public MissionResDto.MissionList missionSummary(Member member, MissionReqDto.MissionSummary request) {
        //페이지 번호/사이즈 결정
        PageRequest pageRequest=PageRequest.of(0, request.size()!=null?request.size():10);

        //도전 가능한 미션 목록 조회
        Page<Mission> missionPage=memberMissionRepository.findHomeMissionList(
                member,
                request.locationId(),
                request.lastId(),
                pageRequest
        );

        //완료한 미션 개수 집계
        Integer completedCount= memberMissionRepository.countCompletedMissionByLocationId(member,  request.locationId());

        //엔티티 -> Dto
        return MissionConverter.toMissionListDto(missionPage, completedCount);
    }

    // 내가 진행중인 미션 조회 (오프셋 기반, memberId from Request Body)
    public MissionResDto.MissionOffsetList getMyMissions(MissionReqDto.GetMyMissionDto request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int page = request.page() != null ? request.page() : 0;
        int size = request.size() != null ? request.size() : 10;
        String status = request.status() != null ? request.status().toString() : MissionStatus.ONGOING.toString();

        Page<MemberMission> missionPage = memberMissionRepository.findByMemberAndStatus(
                member, status, PageRequest.of(page, size));

        return MissionConverter.toMissionOffsetListDto(missionPage);
    }

    @Transactional
    public MissionResDto.CompleteMission completeMission(Member member, Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        memberMission.complete();

        return MissionResDto.CompleteMission.builder()
                .missionId(memberMission.getMission().getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
