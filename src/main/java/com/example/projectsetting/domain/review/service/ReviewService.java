package com.example.projectsetting.domain.review.service;

import com.example.projectsetting.domain.review.dto.ReviewReqDTO;
import com.example.projectsetting.domain.review.dto.ReviewResDTO;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;

public class ReviewService {
    public static ApiResponse<ReviewResDTO.Write> write(BaseSuccessCode code, ReviewReqDTO.Write dto, String authorization) {
    }
}
