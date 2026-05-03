package com.example.projectsetting.domain.review.dto;

public class ReviewReqDTO {

    public record Write(
            Integer rating,
            String description
    ){}
}
