package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;

public class MissionReqDto {
    public record CreateMissionDto(
            Integer reward,
            String deadline,
            String missionSpec
    ) {}

    public record ChallengeMissionDto(
            Long memberId
    ) {}

    public record MissionSummary(
            Long locationId,
            Long lastId,
            Integer size
    ) {}

    public record GetMissionList(
            MissionStatus status,
            Long lastId,
            Integer size
    ) {}
}
