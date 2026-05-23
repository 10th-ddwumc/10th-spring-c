package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    SUCCESS_OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 성공 처리를 완료하였습니다."),

    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션 목록을 조회했습니다."),
    CREATED(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."),
    OK(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션을 조회했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
