package com.example.jini_umc10th.domain.member.dto;

import com.example.jini_umc10th.domain.member.enums.Gender;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record signUpReqDTO(
            @NotNull(message = "약관 동의 여부는 필수입니다.")
            TermsAgreementDTO termsAgreement,
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth,
            String address,
            @NotNull(message = "선호음식은 필수입니다.")
            List<Long> food // foodId 사용
    ) {
        public record TermsAgreementDTO(
                @AssertTrue(message = "만 14세 이상이어야 합니다.")
                boolean isOver14,
                @AssertTrue(message = "서비스 이용약관에 동의해야합니다.")
                boolean serviceTerms,
                @AssertTrue(message = "개인정보 처리방침에 동의해야합니다.")
                boolean privacyPolicy,
                boolean locationTerms,
                boolean marketingTerms
        ) {}
    }

    public record loginReqDTO(
            @NotNull(message = "소셜 로그인 제공자는 필수입니다.")
            SocialProvider socialProvider,  // KAKAO, NAVER, APPLE, GOOGLE
            @NotBlank(message = "소셜 UID는 필수입니다.")
            String socialUid
    ) {}
}
