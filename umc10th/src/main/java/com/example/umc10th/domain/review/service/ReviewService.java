package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDto.CreateReviewResultDto createReview(Long storeId, ReviewReqDto.CreateReviewDto request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, member, store);
        Review savedReview = reviewRepository.save(newReview);

        return ReviewConverter.toReviewResDto(savedReview);
    }

    // 내가 작성한 리뷰 조회 (커서 기반 페이지네이션, ID 순 or 별점 순)
    public ReviewResDto.ReviewListDto getMyReviews(Long memberId, ReviewReqDto.GetMyReviewsDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int size = request.size() != null ? request.size() : 10;
        PageRequest pageRequest = PageRequest.of(0, size);

        boolean sortByStarRating = "STAR_RATING".equalsIgnoreCase(request.sortBy());

        if (sortByStarRating) {
            Page<Review> reviewPage = reviewRepository.findAllByMemberOrderByStarRating(
                    member, request.lastStarRating(), request.lastId(), pageRequest);
            return ReviewConverter.toReviewListByStarRatingDto(reviewPage);
        } else {
            Page<Review> reviewPage = reviewRepository.findAllByMemberOrderById(
                    member, request.lastId(), pageRequest);
            return ReviewConverter.toReviewListByIdDto(reviewPage);
        }
    }
}