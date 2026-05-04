package com.example.umc10th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum SocialType {
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    APPLE("APPLE"),
    GOOGLE("GOOGLE");

    private final String description;
}
