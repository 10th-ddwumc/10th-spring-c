package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.HomeMission;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    //마이페이지 조회
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto)
    {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    //회원가입
    public MemberResDTO.signUp signUp(MemberReqDTO.signUp dto) {
        return null;
    }

    //홈화면 조회
    public MemberResDTO.home home(
            Long memberId,
            String location,
            Long lastId,
            int pageSize)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        //1. 도전 가능 미션 가져오기
        List<HomeMission> missions = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, pageSize);

        if (lastId != null) {
            missions = missionRepository.findByLocationWithCursor(memberId, location, lastId, pageable);
        } else {
            missions = missionRepository.findByLocationWithoutCursor(memberId, location, pageable);
        }

        //2. 선택된 지역의 전체 미션 수 가져오기
        Integer allMissions = memberMissionRepository.countAllMissionsByLocation(memberId, location);

        //3. 선택된 지역의 성공 완료 미션 수 가져오기
        Integer successMission = memberMissionRepository.countSuccessMissionsByLocation(memberId, location);

        return MemberConverter.toHome(location, allMissions, successMission, missions);
    }
}
