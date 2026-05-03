package com.example.projectsetting.domain.review.exception;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import com.example.projectsetting.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
