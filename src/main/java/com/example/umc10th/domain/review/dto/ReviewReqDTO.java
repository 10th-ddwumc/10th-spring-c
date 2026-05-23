package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ReviewReqDTO {

    public record newReview(
            @NotNull(message = "평점은 필수입니다.")
            Double star,

            @NotBlank(message = "리뷰내용은 필수로 입력해주세요.")
            @Size(max = 800, message = "리뷰는 800자 이하로 입력해주세요.")
            String content,

            List<String> photos
    ) {}
}
