package com.example.projectsetting.domain.review.service;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.MemberRepository;
import com.example.projectsetting.domain.mission.entity.Store;
import com.example.projectsetting.domain.mission.exception.StoreException;
import com.example.projectsetting.domain.mission.exception.code.StoreErrorCode;
import com.example.projectsetting.domain.mission.repository.StoreRepository;
import com.example.projectsetting.domain.review.converter.ReviewConverter;
import com.example.projectsetting.domain.review.dto.ReviewReqDTO;
import com.example.projectsetting.domain.review.dto.ReviewResDTO;
import com.example.projectsetting.domain.review.entity.Review;
import com.example.projectsetting.domain.review.exception.ReviewException;
import com.example.projectsetting.domain.review.exception.code.ReviewErrorCode;
import com.example.projectsetting.domain.review.repository.ReviewRepository;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;


    //리뷰 작성 로직
    @Transactional
    public ReviewResDTO.Write write(
            ReviewReqDTO.Write dto,
            String authorization,
            Long storeId
    ) {
        //임시값
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toWrite(dto, member, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toResultWrite(savedReview);
    }

    //리뷰 조회
    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getReviews(
            Long userId,
            Integer pageSize,
            String cursor,
            String query) {
        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0,pageSize);

        long idCursor;
        Integer ratingCursor;
        Slice<Review> reviewList;
        String nextCursor;

        //커서가 있는 경우
        if(!cursor.equals("-1")){

            //커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":
                    //커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[0]);

                    //리뷰 조회 & Where 절에 커서값 기입
                    reviewList = reviewRepository.findReviewByMember_IdAndIdLessThanOrderByIdDesc(
                            userId,
                            idCursor,
                            pageRequest
                    );
                    break;

                case "rating":
                    //커서 타입 변환
                    ratingCursor= Integer.parseInt(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    //리뷰 조회 & Where 절에 커서값 기입
                    reviewList = reviewRepository.findReviewByRatingCursor(
                            userId,
                            ratingCursor,
                            idCursor,
                            pageRequest
                    );
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);

            }
        }else{
            //커서 없이 조회
            switch (query.toLowerCase()){
                case "id":
                    reviewList = reviewRepository.findReviewById(
                            userId,
                            pageRequest);
                    break;
                case "rating":
                    reviewList = reviewRepository.findReviewByRating(
                            userId,
                            pageRequest
                    );
                    break;
                default:
                    throw  new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }
        //다음 커서 계산
        List<Review> reviews = reviewList.getContent();

        if(reviews.isEmpty()){
            nextCursor = null;
        }else{
            Review lastReview = reviews.get(reviews.size() - 1);

            if(query.equals("rating")){
                nextCursor = lastReview.getRating() + ":" + lastReview.getId();
            }else{
                nextCursor = String.valueOf(lastReview.getId());
            }
        }

        //리뷰들 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
