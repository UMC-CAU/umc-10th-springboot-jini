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
            List<MissionResDTO.MissionDTO> missions,   // 미션 목록
            String nextCursor,           // 다음 페이지 커서
            boolean hasNext
    ){
        return MissionResDTO.MissionListResDTO.builder()
                .missions(missions)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
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
