package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/api/{memberId}/stores/{storeId]/reviews")
    public ApiResponse<ReviewResDTO.newReview> addReview(
            @PathVariable("memberId") Long memberId,
            @PathVariable("storeId") Long storeId,
            @RequestBody ReviewReqDTO.newReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.ADD_REVIEW_OK;
        return ApiResponse.onSuccess(code, reviewService.addReview(memberId, storeId, dto));

    }
}
