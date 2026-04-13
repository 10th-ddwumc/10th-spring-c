package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record CreateMissionResultDto(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ChallengeMissionResultDto(
            Long memberMissionId,
            LocalDateTime createdAt
    ) {}
}
