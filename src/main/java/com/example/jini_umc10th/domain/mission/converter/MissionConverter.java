package com.example.jini_umc10th.domain.mission.converter;

import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionDTO toMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionContent(memberMission.getMission().getContent())
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .point(memberMission.getMission().getPoint())
                .dueDate(memberMission.getMission().getDueDate())
                .build();
    }

    public static MissionResDTO.MissionListResDTO toMissionListDTO(
            List<MissionResDTO.MissionDTO> missions   // 미션 목록
    ){
        return MissionResDTO.MissionListResDTO.builder()
                .missions(missions)
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static MissionResDTO.MissionCompleteResDTO toMissionCompleteResDTO(
            Long missionId,
            MissionStatus status,
            int rewardPoint,
            int totalPoint
    ) {
        return MissionResDTO.MissionCompleteResDTO.builder()
                .missionId(missionId)
                .status(status)
                .rewardPoint(rewardPoint)
                .totalPoint(totalPoint)
                .build();
    }
}
