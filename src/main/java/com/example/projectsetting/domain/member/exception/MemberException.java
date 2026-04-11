package com.example.projectsetting.domain.member.exception;

import com.example.projectsetting.global.apiPayload.code.BaseErrorCode;
import com.example.projectsetting.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
  public MemberException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
