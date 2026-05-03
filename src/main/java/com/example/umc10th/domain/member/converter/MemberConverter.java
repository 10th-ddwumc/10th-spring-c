package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.HomeMission;

import java.util.List;

public class MemberConverter {

    //마이페이지 조회
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    //홈화면 조회
    public static MemberResDTO.home toHome(
            String location,
            Integer allMissions,
            Integer successMissions,
            List<HomeMission> missions
    ) {
        return MemberResDTO.home.builder()
                .location(location)
                .allMissionsCount(allMissions)
                .successMissionsCount(successMissions)
                .missions(missions)
                .build();
    }
}
