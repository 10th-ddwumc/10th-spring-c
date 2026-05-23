package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "사용자를 찾을 수 없습니다."),

    ALREADY_EXISTS_EMAIL(HttpStatus.CONFLICT,
            "MEMBER_409_1",
            "이미 존재하는 이메일입니다."),

    REQUIRED_TERMS_NOT_AGREED(
            HttpStatus.BAD_REQUEST,
            "MEMBER_400_1",
            "필수 약관에 동의해야 합니다."
    ),

    INVALID_FOOD(
            HttpStatus.BAD_REQUEST,
            "MEMBER_400_2",
            "존재하지 않는 음식 카테고리입니다."
    ),
    NOT_SUPPORT_SOCIAL_PROVIDER(
            HttpStatus.BAD_REQUEST,
            "MEMBER_400_3",
            "지원하지 않는 소셜 로그인 제공자입니다."
    ),

    INVALID_PASSWORD(
            HttpStatus.BAD_REQUEST,
            "MEMBER_400_3",
            "비밀번호가 일치하지 않습니다."
    ),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
