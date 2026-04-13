package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {
    public record CreateMissionDto(
            Integer reward,
            String deadline,
            String missionSpec
    ) {}

    public record ChallengeMissionDto(
            Long memberId
    ) {}
}
