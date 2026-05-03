package com.example.jini_umc10th.domain.review.exception;

import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.jini_umc10th.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) { super(errorCode); }
}
