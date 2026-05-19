package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class ReviewReqDto {

    public record CreateReviewDto(
            @NotNull(message = "사용자 ID는 필수입니다.") Long memberId,
            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
            Integer starRating,
            @NotBlank(message = "리뷰 내용은 필수입니다.") String content,
            List<String> reviewImageUrls
    ) {}

    // 내가 작성한 리뷰 목록 조회 (커서 기반)
    public record GetMyReviewsDto(
            Long lastId,
            Integer lastStarRating,
            Integer size,
            String sortBy   // "ID" or "STAR_RATING"
    ) {}
}