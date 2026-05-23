package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record Success(
            Long id
    ) {}

    @Builder
    public record GetMissions(
            Long id,
            String storeName,
            Integer price,
            Integer point
    ) {}

    //가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ) {}

}
