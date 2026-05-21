package com.example.jini_umc10th.domain.review.exception.code;

import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT,
            "REVIEW409_1",
            "이미 작성한 리뷰가 있습니다."),
    REVIEW_NOT_AUTHORIZED(HttpStatus.FORBIDDEN,
            "REVIEW403_1",
            "리뷰 수정/삭제 권한이 없습니다."),
    INVALID_SCORE(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "유효하지 않은 별점입니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "REVIEW400_2",
            "잘못된 쿼리입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}