package com.example.projectsetting.domain.member.dto;

import java.util.List;

public class MemberResDTO {


    public record Signup(
        Long userId,
        String name
    ){}

    public record Dashboard(
           String region,
           Integer currentCount,
           List<MissionCard> missions
    ){}

    public record MissionCard(
            Long missionId,
            String store,
            String category,
            Integer point,
            Integer reward,
            Integer price,
            String dDay
    ){}

    public record Mypage(
            Long memberId,
            String socialId,
            String email,
            String phone,
            Integer point,
            String profile
    ){}


}
