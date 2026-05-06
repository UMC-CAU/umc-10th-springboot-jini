package com.example.jini_umc10th.domain.member.dto;

import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record signUpResDTO(
            Long memberId,         // 생성된 회원 ID
            String name,           // 이름
            LocalDateTime createdAt // 가입 일시
    ){}

    @Builder
    public record loginResDTO(
            Long memberId,
            String name
    ){}

    // 홈화면 조회 응답 DTO
    @Builder
    public record homeResDTO(
            List<MissionResDTO.MissionDTO> missions,
            String nextCursor,    // 다음 페이지 커서
            boolean hasNext,      // 다음 페이지 존재 여부
            String areaName,
            int completedNum

    ) {}

    // 마이페이지 조회 응답 DTO
    @Builder
    public record profileResDTO(
            String name,
            String emailAddress,
            Boolean isEmailVerified,
            String phoneNumber,
            Boolean isPhoneVerified,
            int point
    ) {}
}
