package com.example.projectsetting.domain.member.exception.code;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER400_1",
            "해당 멤버를 찾을 수 없습니다"),

    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "NOT_SUPPOT400_1",
            "지원하지 않는 소셜 제공자입니다."),;
    private final HttpStatus status;
    private final String code;
    private final String message;



}
