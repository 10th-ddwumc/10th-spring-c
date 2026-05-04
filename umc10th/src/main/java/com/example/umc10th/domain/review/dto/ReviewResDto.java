package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDto {

    @Builder
    public record CreateReviewResultDto(
            Long reviewId,
            LocalDateTime createdAt
    )
    {}
}
