package com.example.umc10th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Gender {
    NONE("선택안함"),
    MALE("남자"),
    FEMALE("여자");

    private final String description;
}
