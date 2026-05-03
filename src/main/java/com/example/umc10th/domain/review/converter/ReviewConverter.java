package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;


public class ReviewConverter {

    public static ReviewResDTO.newReview toNewReview(Review savedReview) {
        return ReviewResDTO.newReview.builder()
                .id(savedReview.getId())
                .build();
    }

    public static Review toReview(Member member, Store store, ReviewReqDTO.newReview dto) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();
    }
}
