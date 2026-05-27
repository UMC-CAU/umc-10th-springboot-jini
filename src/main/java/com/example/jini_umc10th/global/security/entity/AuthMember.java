package com.example.jini_umc10th.global.security.entity;

import com.example.jini_umc10th.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return null; // JWT 토큰 기반시에는 member.getPassword() 반환
    }

    @Override
    public String getUsername() {
        return member.getSocialUid();
    } // JWT 토큰 기반시에는 member.getEmailAddress() 반환
}
