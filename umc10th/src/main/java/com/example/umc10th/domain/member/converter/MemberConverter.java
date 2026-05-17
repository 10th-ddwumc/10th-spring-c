package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfoResDTO(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneNumberVerified(member.isPhoneNumberVerified())
                .point(member.getPoint())
                .build();
    }

    // 회원가입 DTO → Member 엔티티
    public static Member toMember(MemberReqDTO.SignUpDto dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .birth(dto.birth())
                .gender(dto.gender() != null ? dto.gender() : Gender.NONE)
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .phoneNumber(dto.phoneNumber())
                .build();
    }

    // Member 엔티티 → 회원가입 응답 DTO
    public static MemberResDTO.SignUpResultDto toSignUpResultDto(Member member) {
        return MemberResDTO.SignUpResultDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
