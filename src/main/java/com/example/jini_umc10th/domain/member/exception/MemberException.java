package com.example.jini_umc10th.domain.member.exception;

import com.example.jini_umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.jini_umc10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
