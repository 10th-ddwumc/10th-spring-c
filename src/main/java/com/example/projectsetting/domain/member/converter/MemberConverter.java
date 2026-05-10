package com.example.projectsetting.domain.member.converter;

import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.entity.Mission;

import java.util.ArrayList;
import java.util.List;

public class MemberConverter {

    //
    public static MemberResDTO.Mypage toResultMypage(Member member){
        return MemberResDTO.Mypage.builder()
                .memberId(member.getId())
                .socialId(member.getSocialId())
                .email(member.getEmail())
                .phone(member.getPhone())
                .point(member.getPoint())
                .profile(member.getProfile())
                .build();
    }

    public static MemberResDTO.Dashboard toResultDashboard(
            Member member,
            List<Mission> missions){
        List<MemberResDTO.MissionCard> missionCards = new ArrayList<>();

        for(Mission mission : missions){
            MemberResDTO.MissionCard card = MemberResDTO.MissionCard.builder()
                    .missionId(mission.getId())
                    .store(mission.getStore().getName())
                    .category(mission.getStore().getCategory().name())
                    .point(mission.getPoint())
                    .reward(mission.getReward().intValue())
                    .dDay("D-7")
                    .build();

            missionCards.add(card);
        }
        return MemberResDTO.Dashboard.builder()
                .region(member.getAddress())
                .currentCount(0)
                .missions(missionCards)
                .build();
    }

    public static Member toMember(MemberReqDTO.Signup dto) {
        return Member.builder()
                .name(dto.name())
                .phone(dto.phoneNum())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .build();
    }

    public static MemberResDTO.Signup toSignup(Member member) {
        return MemberResDTO.Signup.builder()
                .userId(member.getId())
                .name(member.getName())
                .build();
    }
}
