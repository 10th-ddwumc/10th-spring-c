package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record newReview(
            Double star,
            String content,
            List<String> photos
    ) {}
}
