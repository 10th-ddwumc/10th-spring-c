package com.example.projectsetting.domain.review.dto;

import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record Write(
            @NotNull(message = "별점은 필수입니다.")
            Integer rating,
            @NotNull(message = "내용작성은 필수입니다.")
            String content
    ){}
}
