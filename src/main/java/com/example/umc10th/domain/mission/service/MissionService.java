package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
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
public class MissionService {

    private MissionRepository missionRepository;
    private MemberRepository memberRepository;

    private MissionConverter missionConverter;

    //완료/미완료 미션 조회
    public List<MissionResDTO.GetMissions> getMissions(Boolean isSuccess, Long memberId, LocalDate lastEndDate, Long lastId, int pageSize) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        List<Mission> myMissions = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, pageSize);

        if (lastEndDate != null && lastId != null) {
            myMissions =  missionRepository.findByIsSuccessWithCursor(memberId, isSuccess, lastEndDate, lastId, pageable);
        } else {
            myMissions =  missionRepository.findByIsSuccessWithoutCursor(memberId, isSuccess, pageable);
        }

        List<MissionResDTO.GetMissions> result = new ArrayList<>();

        for (Mission myMission : myMissions) {
            MissionResDTO.GetMissions myMissionDTO = missionConverter.toGetMissions(myMission);
            result.add(myMissionDTO);
        }
        return result;
    }

    //미션 성공 누르기
    public MissionResDTO.Success success(Long missionId, Long memberId) {
        return null;
    }
}
