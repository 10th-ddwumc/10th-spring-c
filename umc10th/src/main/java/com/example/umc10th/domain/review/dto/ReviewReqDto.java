package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDto {
    public record CreateReviewDto(
            Integer starRating,
            String content,
            List<String> reviewImageUrls
    ){}
}