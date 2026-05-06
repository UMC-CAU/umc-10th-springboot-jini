package com.example.jini_umc10th.domain.member.converter;

import com.example.jini_umc10th.domain.member.dto.MemberResDTO;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;

import java.time.LocalDateTime;
import java.util.List;

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

    public static MemberResDTO.profileResDTO toProfileResDTO(
            String name,
            String emailAddress,
            Boolean isEmailVerified,
            String phoneNumber,
            Boolean isPhoneVerified,
            int point
    ) {
        return MemberResDTO.profileResDTO.builder()
                .name(name)
                .emailAddress(emailAddress)
                .isEmailVerified(isEmailVerified)
                .phoneNumber(phoneNumber)
                .isPhoneVerified(isPhoneVerified)
                .point(point)
                .build();
    }

    public static MemberResDTO.homeResDTO toHomeResDTO(
            List<MissionResDTO.MissionDTO> missions,
            String nextCursor,    // 다음 페이지 커서
            boolean hasNext,      // 다음 페이지 존재 여부
            String areaName,
            int completedNum
    ){
        return MemberResDTO.homeResDTO.builder()
                .missions(missions)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .areaName(areaName)
                .completedNum(completedNum)
                .build();
    }

}
