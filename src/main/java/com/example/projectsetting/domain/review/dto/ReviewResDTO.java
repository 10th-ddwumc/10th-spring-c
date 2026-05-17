package com.example.projectsetting.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record Write(
            Long reviewId,
            Integer rating,
            String content,
            String storeName
    ){}

    //커서 기반 페이지네이션
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

    //리뷰 조회
    @Builder
    public record GetReview(
            Long reviewId,
            String nickname,
            Integer rating,
            String content,
            String storeName,
            String createdAt
    ){}
}
