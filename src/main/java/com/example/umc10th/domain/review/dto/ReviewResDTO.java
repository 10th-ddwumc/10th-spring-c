package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record newReview(
            Long id
    ) {}

    //내가 작성한 리뷰 조회
    @Builder
    public record MyReview(
            Long id,
            Double star,
            String content,
            String memberName,
            LocalDateTime createdAt,
            ReplyDTO reply

    ) {}

    //리뷰 답글
    @Builder
    public record ReplyDTO(
            Long id,
            String content,
            LocalDateTime createdAt
    ) {}
}
