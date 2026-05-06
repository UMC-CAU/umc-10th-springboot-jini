package com.example.jini_umc10th.domain.mission.exception.code;

import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다."),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "해당 식당을 찾을 수 없습니다."),
    AREA_NOT_FOUND(HttpStatus.NOT_FOUND,
            "AREA404_1",
            "해당 지역을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
