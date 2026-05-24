package com.example.jini_umc10th.global.security.util;

import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityResponseUtil {

    public static void sendErrorResponse(
            HttpServletResponse response, // HTTP 응답 객체
            BaseErrorCode code            // 반환할 에러 코드 (UNAUTHORIZED, FORBIDDEN 등)
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Java 객체를 JSON으로 변환해주는 객체

        response.setContentType("application/json;charset=UTF-8"); // 응답 형식을 JSON으로 설정
        response.setStatus(code.getStatus().value());              // HTTP 상태코드 설정 (401, 403 등)

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null); // 프로젝트 공통 응답 형식으로 에러 응답 생성

        objectMapper.writeValue(response.getOutputStream(), errorResponse); // 생성한 응답 객체를 JSON으로 변환해서 실제 응답에 작성
    }
}
