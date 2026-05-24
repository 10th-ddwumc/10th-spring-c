package com.example.projectsetting.global.security.dto;

import com.example.projectsetting.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
