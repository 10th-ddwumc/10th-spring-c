package com.example.projectsetting.domain.member.converter;

import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Food;
import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.entity.Term;
import com.example.projectsetting.domain.member.entity.mapping.MemberFood;
import com.example.projectsetting.domain.member.entity.mapping.MemberTerm;
import com.example.projectsetting.domain.member.enums.SocialType;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.global.security.dto.OAuthDTO;

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

    public static Member toSignup(MemberReqDTO.Signup dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .password(encodedPassword)
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .phone(dto.phoneNum())
                .email(dto.email())
                .socialId(dto.email())
                .socialType(SocialType.NONE)
                .build();
    }

    public static MemberResDTO.Signup toResultSignup(Member member, String accessToken) {
        return MemberResDTO.Signup.builder()
                .memberId(member.getId())
                .name(member.getName())
                .accessToken(accessToken)
                .build();
    }

    public static MemberTerm toMemberTerm(Member member, Term term){
        return MemberTerm.builder()
                .member(member)
                .term(term)
                .build();
    }

    public static MemberFood toMemberFood(Member member, Food food){
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken){
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }


    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .socialType(dto.getSocialType())
                .socialId(dto.getSocialUid())
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .build();
    }
}
