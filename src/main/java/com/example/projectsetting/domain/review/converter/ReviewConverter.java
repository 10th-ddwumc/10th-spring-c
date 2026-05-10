package com.example.projectsetting.domain.review.converter;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.entity.Store;
import com.example.projectsetting.domain.review.dto.ReviewReqDTO;
import com.example.projectsetting.domain.review.dto.ReviewResDTO;
import com.example.projectsetting.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    //작성한 리뷰를 엔티티형식으로 바꾸기
    public static Review toWrite(
            ReviewReqDTO.Write dto, Member member, Store store
    ){
        return Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    //저장된 리뷰를 dto형식으로 바꾸기
    public static ReviewResDTO.Write toResultWrite(Review review){
        return ReviewResDTO.Write.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .build();
    }

    // 커서페이지네이션 툴 생성
    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return  ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .createdAt(String.valueOf(review.getCreated()))
                .build();
    }
}
