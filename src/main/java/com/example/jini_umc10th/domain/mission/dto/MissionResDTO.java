package com.example.jini_umc10th.domain.mission.dto;

import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDTO(
            Long missionId,
            String missionContent,
            String restaurantName,
            int point,
            LocalDate dueDate
    ) {}

    @Builder
    public record MissionListResDTO(
            List<MissionDTO> missions,   // 미션 목록
            String nextCursor,           // 다음 페이지 커서
            boolean hasNext              // 다음 페이지 여부
    ) {}

    @Builder
    public record MissionCompleteResDTO(
            Long missionId,
            MissionStatus status,
            int rewardPoint,
            int totalPoint
    ){}
}
