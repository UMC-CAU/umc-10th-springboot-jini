package com.example.jini_umc10th.global.security.service;

import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import com.example.jini_umc10th.domain.member.exception.MemberException;
import com.example.jini_umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.jini_umc10th.domain.member.repository.MemberRepository;
import com.example.jini_umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmailAddress(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

    public UserDetails loadUserByUidAndSocialProvider(
            String uid,
            SocialProvider socialProvider
    ) throws UsernameNotFoundException {
        // DB에서 기존 회원 정보 조회 & 인증 객체 생성
        Member member = memberRepository.findBySocialProviderAndSocialUid(socialProvider, uid)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

}
