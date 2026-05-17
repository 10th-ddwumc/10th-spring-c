package com.example.projectsetting.domain.review.controller;

import com.example.projectsetting.domain.mission.exception.code.MissionSuccessCode;
import com.example.projectsetting.domain.review.dto.ReviewReqDTO;
import com.example.projectsetting.domain.review.dto.ReviewResDTO;
import com.example.projectsetting.domain.review.exception.code.ReviewSuccessCode;
import com.example.projectsetting.domain.review.service.ReviewService;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import com.example.projectsetting.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성하기
    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.Write> write(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.Write dto
            ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        ReviewResDTO.Write result = reviewService.write(dto,authorization,storeId);
        return ApiResponse.onSuccess(code, result);
    }

    //내가 생성한 리뷰 조회하기
    @GetMapping("/{userId}")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getReviews(
            @PathVariable Long userId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviews(userId,pageSize, cursor, query));
    }
}
