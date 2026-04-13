package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {
    public record CreateReviewDto(
            Long memberId,
            Float rating,
            String content
    ) {}
}