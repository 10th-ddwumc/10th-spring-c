package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_SUMMARIZED(HttpStatus.OK, "MISSION200_1", "홈 화면 미션 목록 조회 성공"),
    MISSION_CHECKED(HttpStatus.OK, "MISSION200_2", "미션 목록 조회 성공"),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION200_3", "미션 완료 처리 성공"),
    MISSION_MY_CHECKED(HttpStatus.OK, "MISSION200_4", "내가 진행중인 미션 목록 조회 성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
