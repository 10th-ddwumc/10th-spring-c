package com.example.projectsetting.domain.member.service;

import com.example.projectsetting.domain.member.converter.MemberConverter;
import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.MemberRepository;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.repository.MissionRepository;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public ApiResponse<MemberResDTO.Signup> signup(BaseSuccessCode code, MemberReqDTO.Signup dto) {

        return null;
    }

    public MemberResDTO.Dashboard getDashboard(String authorization) {

        //임시값
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.NOT_FOUND));
        String location = member.getAddress();

        List<Mission> missions = missionRepository.findMissionByLocation(location, memberId);

        return MemberConverter.toResultDashboard(member,missions);
    }


    public MemberResDTO.Mypage getMypage(String authorization) {

        //임시값
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toResultMypage(member);
    }


}
