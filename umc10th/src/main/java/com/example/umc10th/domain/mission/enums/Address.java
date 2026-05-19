package com.example.umc10th.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Address {
    ANAM("안암동"),
    SAMSUNG("삼성동"),
    YEOUIDO("여의도동"),
    PANGYO("판교동"),
    SHINCHON("신촌동");

    private final String description;
}
