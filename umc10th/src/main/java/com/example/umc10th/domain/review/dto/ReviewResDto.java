package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    @Builder
    public record CreateReviewResultDto(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 내가 작성한 리뷰 단건 (사진 제외)
    @Builder
    public record ReviewItemDto(
            Long reviewId,
            Integer starRating,
            String content,
            String storeName,
            String replyContent,
            LocalDateTime createdAt
    ) {}

    // 내가 작성한 리뷰 목록 (커서 기반 페이지네이션)
    @Builder
    public record ReviewListDto(
            List<ReviewItemDto> reviewList,
            Long nextLastId,
            Integer nextLastStarRating,
            Boolean hasMore
    ) {}
}
