package com.example.jini_umc10th.domain.mission.controller;

import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.jini_umc10th.domain.mission.service.MissionService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 회원 미션 조회
    @GetMapping("/api/members/me/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionDTO>> getMemberMission(
            @RequestParam Long memberId,
            @RequestParam MissionStatus missionStatus,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMemberMissions(memberId, missionStatus, pageSize, pageNumber, sort));
    }

    // 미션 완료
    @PostMapping("/api/missions/{missionId}/completed")
    public ApiResponse<MissionResDTO.MissionCompleteResDTO> completeMission(
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }
}
