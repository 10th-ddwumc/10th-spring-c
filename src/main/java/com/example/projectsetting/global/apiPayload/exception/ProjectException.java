package com.example.projectsetting.global.apiPayload.exception;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
