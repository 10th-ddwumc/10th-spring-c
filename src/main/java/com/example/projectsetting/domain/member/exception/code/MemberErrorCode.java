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
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
