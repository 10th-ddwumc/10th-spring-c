package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.pagination.PaginationConverter;
import com.example.umc10th.global.pagination.PaginationResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성
    public ReviewResDTO.newReview addReview(Long memberId, Long storeId, ReviewReqDTO.newReview dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(member, store, dto);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toNewReview(savedReview);
    }

    //사용자가 작성한 리뷰 목록 조회
    public PaginationResDTO.CursorPagination<ReviewResDTO.MyReview> getMyReviews(Long memberId, Integer pageSize, String cursor, String query) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        //커서가 있는 경우
        if (!cursor.equals("-1")) {

            //커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    //커서 타입 변솬
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    //가게 내 미션들 조회 & where절에 커서값 기입
                    reviewList = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }

        }

        //커서 없이 조회
        reviewList = reviewRepository.findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);

        nextCursor = reviewList.getContent()
                .get(reviewList.getContent().size() - 1)
                .getId()
                + ":" +
                reviewList.getContent()
                        .get(reviewList.getContent().size() - 1)
                        .getId();

        return PaginationConverter.toCursorPagination(
                reviewList.map(ReviewConverter::toMyReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
