package com.example.jini_umc10th.global.security.dto;

import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialProvider getSocialType() {
        return SocialProvider.KAKAO;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }
}
