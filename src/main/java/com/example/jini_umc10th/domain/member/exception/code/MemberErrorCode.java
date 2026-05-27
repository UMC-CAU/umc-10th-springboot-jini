package com.example.jini_umc10th.domain.member.exception.code;

import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 올바르지 않습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "지원하지 않는 소셜 로그인 제공자입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
