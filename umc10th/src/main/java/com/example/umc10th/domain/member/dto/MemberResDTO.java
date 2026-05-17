package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Boolean phoneNumberVerified,
            Integer point
    ) {}

    // 회원가입 응답
    @Builder
    public record SignUpResultDto(
            Long memberId,
            String email,
            LocalDateTime createdAt
    ) {}
}