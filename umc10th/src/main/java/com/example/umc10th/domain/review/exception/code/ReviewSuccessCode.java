package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_LIST_CHECKED(HttpStatus.OK, "REVIEW200_1", "내가 작성한 리뷰 목록 조회 성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
