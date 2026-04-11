package com.example.projectsetting.domain.review.dto;

public class ReviewResDTO {

    public record Write(
            Long reviewId,
            Integer rating,
            String description,
            String name
    ){}
}
