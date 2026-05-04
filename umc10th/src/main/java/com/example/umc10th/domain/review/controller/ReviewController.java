package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDto.CreateReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDto.CreateReviewDto request) {
        return null; // Service 로직 연동 시 반환값 수정
    }
}
