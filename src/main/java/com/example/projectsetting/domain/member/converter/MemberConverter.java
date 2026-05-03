package com.example.projectsetting.domain.member.converter;

import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Member;

import java.util.List;

public class MemberConverter {

    public static MemberResDTO.Mypage toMypage(Member member){
        return MemberResDTO.Mypage.builder()
                .memberId(member.getId())
                .socialId(member.getSocialId())
                .email(member.getEmail())
                .phone(member.getPhone())
                .point(member.getPoint())
                .profile(member.getProfile())
                .build();
    }

    public static MemberResDTO.Dashboard toDashboard(Member member){
        return MemberResDTO.Dashboard.builder()
                .region(member.getAddress())
                .currentCount(0)
                .missions(List.of())
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
