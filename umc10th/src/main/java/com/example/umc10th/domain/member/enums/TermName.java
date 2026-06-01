package com.example.umc10th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TermName {
    GE_OVER_14("만 14세 이상입니다."),
    SERVICE_TERM("서비스 이용약관"),
    PRIVACY_POLICY("개인 정보 처리 방침"),
    LOCATION_INFO("위치정보 제공"),
    MARKETING_CONSENT("마케팅 수신 동의");

    private final String description;
}
