package com.example.umc10th.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum MissionStatus {
    ONGOING("진행 중"),
    COMPLETED("진행 완료");

    private final String description;
}
