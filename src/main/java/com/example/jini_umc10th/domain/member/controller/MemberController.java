package com.example.jini_umc10th.domain.member.controller;

import com.example.jini_umc10th.domain.member.dto.MemberReqDTO;
import com.example.jini_umc10th.domain.member.dto.MemberResDTO;
import com.example.jini_umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.jini_umc10th.domain.member.service.MemberService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 홈화면 조회
    @GetMapping("/api/members/me")
    public ApiResponse<MemberResDTO.HomeResDTO> getHome(
            @RequestParam Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam String regionId,
            @RequestParam(defaultValue = "20") int size
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getHome(memberId, cursor, regionId, size));
    }

    // 회원가입
    @PostMapping("/auth/members")
    public ApiResponse<MemberResDTO.signUpResDTO> signUp(
            @RequestBody MemberReqDTO.signUpReqDTO dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.createUser(dto));
    }

    //로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.loginResDTO> login(
            @RequestBody MemberReqDTO.loginReqDTO dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.login(dto));
    }
}