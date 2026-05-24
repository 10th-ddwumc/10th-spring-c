package com.example.projectsetting.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record Login(
            String accessToken
    ){}

    @Builder
    public record Signup(
        Long memberId,
        String name,
        String email,
        String accessToken
    ){}

    @Builder
    public record Dashboard(
           String region,
           Integer currentCount,
           List<MissionCard> missions
    ){}

    @Builder
    public record MissionCard(
            Long missionId,
            String store,
            String category,
            Integer point,
            Integer reward,
            Integer price,
            String dDay
    ){}

    @Builder
    public record Mypage(
            Long memberId,
            String socialId,
            String email,
            String phone,
            Integer point,
            String profile
    ){}


}
