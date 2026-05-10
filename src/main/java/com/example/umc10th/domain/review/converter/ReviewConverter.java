package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
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

    public static ReviewResDTO.MyReview toMyReview(Review review) {
        Reply reply = review.getReply();

        return ReviewResDTO.MyReview.builder()
                .id(review.getId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .reply(reply != null ? toReply(reply) : null)
                .build();
    }

    public static ReviewResDTO.ReplyDTO toReply(Reply reply) {
        return ReviewResDTO.ReplyDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
