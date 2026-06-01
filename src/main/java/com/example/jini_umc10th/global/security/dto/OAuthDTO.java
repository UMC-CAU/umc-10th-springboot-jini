package com.example.jini_umc10th.global.security.dto;

import com.example.jini_umc10th.domain.member.enums.SocialProvider;

public interface OAuthDTO {
    SocialProvider getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
