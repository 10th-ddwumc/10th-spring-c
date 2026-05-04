package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;

import java.time.LocalDateTime;

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
}
