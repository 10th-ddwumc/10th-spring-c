package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/stores/{storeId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResDto.CreateReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDto.CreateReviewDto request) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(storeId, request));
    }

    // 내가 작성한 리뷰 목록 조회 (커서 기반, ID 순 or 별점 순)
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDto.ReviewListDto> getMyReviews(
            @PathVariable Long memberId,
            @ModelAttribute ReviewReqDto.GetMyReviewsDto request) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_CHECKED,
                reviewService.getMyReviews(memberId, request));
    }
}
