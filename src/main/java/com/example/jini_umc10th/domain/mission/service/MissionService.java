package com.example.jini_umc10th.domain.mission.service;

import com.example.jini_umc10th.domain.mission.controller.MissionController;
import com.example.jini_umc10th.domain.mission.converter.MissionConverter;
import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    //private final MissionRepository missionRepository;

    public MissionResDTO.MissionListResDTO getMissions(
            String cursor,
            MissionStatus status,
            int size
    ){
        // TODO : 미션 조회 구현
        return MissionConverter.toMissionListDTO(null, cursor, false);
    }

    public MissionResDTO.MissionCompleteResDTO completeMission(
            Long missionId
    ){
        // TODO : 미션 완료 구현
        return MissionConverter.toMissionCompleteResDTO(1L, MissionStatus.COMPLETED, 5, 10); // 임시
    }
}
