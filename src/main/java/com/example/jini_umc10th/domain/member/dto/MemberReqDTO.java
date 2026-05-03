package com.example.jini_umc10th.domain.member.dto;

import com.example.jini_umc10th.domain.member.enums.Gender;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record signUpReqDTO(
            TermsAgreementDTO termsAgreement,
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            List<Long> food // foodId 사용
    ) {
        public record TermsAgreementDTO(
                boolean isOver14,
                boolean serviceTerms,
                boolean privacyPolicy,
                boolean locationTerms,
                boolean marketingTerms
        ) {}
    }

    public record loginReqDTO(
            SocialProvider socialProvider,  // KAKAO, NAVER, APPLE, GOOGLE
            String socialUid
    ) {}
}
