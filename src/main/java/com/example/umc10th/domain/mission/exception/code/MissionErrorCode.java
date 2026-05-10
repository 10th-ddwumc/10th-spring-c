package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode{
    QUERY_NOT_VALID(HttpStatus.NOT_FOUND,
                "MISSION400_1",
                "유효하지 않은 query값입니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
