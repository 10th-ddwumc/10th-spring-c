package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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

    // 내가 진행중인 미션 조회 (오프셋 기반 페이지네이션)
    public record GetMyMissionDto(
            @NotNull(message = "사용자 ID는 필수입니다.") Long memberId,
            MissionStatus status,
            @Min(value = 0, message = "페이지는 0 이상이어야 합니다.") Integer page,
            @Min(value = 1, message = "사이즈는 1 이상이어야 합니다.") Integer size
    ) {}
}
