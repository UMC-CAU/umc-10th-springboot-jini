package com.example.jini_umc10th.domain.mission.controller;

import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.jini_umc10th.domain.mission.service.MissionService;
import com.example.jini_umc10th.global.apiPayload.ApiResponse;
import com.example.jini_umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/api/members/me/missions")
    public ApiResponse<MissionResDTO.MissionListResDTO> getMission(
            @RequestParam Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam MissionStatus missionStatus,
            @RequestParam(defaultValue = "20") int size
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(memberId, cursor, missionStatus, size));
    }

    @PostMapping("/api/missions/{missionId}/completed")
    public ApiResponse<MissionResDTO.MissionCompleteResDTO> completeMission(
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }
}
