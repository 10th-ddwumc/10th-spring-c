package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewReqDto.CreateReviewDto request, Member member, Store store) {
        return Review.builder()
                .starRating(request.starRating())
                .content(request.content())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDto.CreateReviewResultDto toReviewResDto(Review review) {
        return new ReviewResDto.CreateReviewResultDto(review.getId(), LocalDateTime.now());
    }

    public static ReviewResDto.ReviewItemDto toReviewItemDto(Review review) {
        return ReviewResDto.ReviewItemDto.builder()
                .reviewId(review.getId())
                .starRating(review.getStarRating())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .replyContent(review.getReply() != null ? review.getReply().getContent() : null)
                .createdAt(review.getCreatedAt())
                .build();
    }

    // ID 순 커서 기반 리스트 변환
    public static ReviewResDto.ReviewListDto toReviewListByIdDto(Page<Review> reviewPage) {
        List<ReviewResDto.ReviewItemDto> reviewDtos = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewItemDto)
                .collect(Collectors.toList());

        Long nextLastId = reviewDtos.isEmpty() ? null : reviewDtos.get(reviewDtos.size() - 1).reviewId();

        return ReviewResDto.ReviewListDto.builder()
                .reviewList(reviewDtos)
                .nextLastId(nextLastId)
                .hasMore(reviewPage.hasNext())
                .build();
    }

    // 별점 순 커서 기반 리스트 변환
    public static ReviewResDto.ReviewListDto toReviewListByStarRatingDto(Page<Review> reviewPage) {
        List<ReviewResDto.ReviewItemDto> reviewDtos = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewItemDto)
                .collect(Collectors.toList());

        ReviewResDto.ReviewItemDto lastItem = reviewDtos.isEmpty() ? null : reviewDtos.get(reviewDtos.size() - 1);

        return ReviewResDto.ReviewListDto.builder()
                .reviewList(reviewDtos)
                .nextLastId(lastItem != null ? lastItem.reviewId() : null)
                .nextLastStarRating(lastItem != null ? lastItem.starRating() : null)
                .hasMore(reviewPage.hasNext())
                .build();
    }
}
