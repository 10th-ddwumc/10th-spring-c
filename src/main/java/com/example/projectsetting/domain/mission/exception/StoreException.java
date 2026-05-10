package com.example.projectsetting.domain.mission.exception;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import com.example.projectsetting.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
