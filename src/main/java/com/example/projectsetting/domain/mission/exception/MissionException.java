package com.example.projectsetting.domain.mission.exception;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import com.example.projectsetting.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
