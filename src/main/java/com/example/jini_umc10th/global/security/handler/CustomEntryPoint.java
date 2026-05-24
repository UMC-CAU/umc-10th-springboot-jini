package com.example.jini_umc10th.global.security.handler;

import com.example.jini_umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.jini_umc10th.global.security.util.SecurityResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        SecurityResponseUtil.sendErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}
