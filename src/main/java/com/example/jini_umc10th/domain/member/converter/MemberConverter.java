package com.example.jini_umc10th.domain.member.converter;

import com.example.jini_umc10th.domain.member.dto.MemberResDTO;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResDTO.signUpResDTO toSignUpResDTO(
            Long memberId,
            String name,
            LocalDateTime createdAt
    ) {
        return MemberResDTO.signUpResDTO.builder()
                .memberId(memberId)
                .name(name)
                .createdAt(createdAt)
                .build();
    }

    public static MemberResDTO.loginResDTO toLoginResDTO(
            SocialProvider socialProvider,  // KAKAO, NAVER, APPLE, GOOGLE
            String socialUid
    ) {
        return MemberResDTO.loginResDTO.builder()
                .memberId(1L) // 임시
                .name("kingsejin") // 임시
                .build();
    }

}
