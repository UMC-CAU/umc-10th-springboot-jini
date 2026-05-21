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
            List<MissionDTO> missions   // 미션 목록

    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}


    @Builder
    public record MissionCompleteResDTO(
            Long missionId,
            MissionStatus status,
            int rewardPoint,
            int totalPoint
    ){}
}
