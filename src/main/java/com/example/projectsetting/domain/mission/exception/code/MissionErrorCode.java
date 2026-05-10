package com.example.projectsetting.domain.mission.exception.code;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "잘못된 조회 조건입니다."
    );
    private final HttpStatus status;
    private final  String code;
    private final String message;
}
