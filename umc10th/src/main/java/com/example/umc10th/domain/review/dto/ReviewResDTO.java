package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateReviewResultDto(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
