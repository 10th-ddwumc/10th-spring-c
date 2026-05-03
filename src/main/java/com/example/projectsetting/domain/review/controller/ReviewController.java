package com.example.projectsetting.domain.review.controller;

import com.example.projectsetting.domain.review.dto.ReviewReqDTO;
import com.example.projectsetting.domain.review.dto.ReviewResDTO;
import com.example.projectsetting.domain.review.service.ReviewService;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import com.example.projectsetting.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResDTO.Write> write(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.Write dto
            ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ReviewService.write(code,dto,authorization);
    }

}
