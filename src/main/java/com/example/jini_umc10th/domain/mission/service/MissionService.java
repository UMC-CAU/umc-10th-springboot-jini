package com.example.jini_umc10th.domain.mission.service;

import com.example.jini_umc10th.domain.mission.converter.MissionConverter;
import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.jini_umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListResDTO getMissions(
            Long memberId,
            String cursor,
            MissionStatus status,
            int size
    ){
        Long cursorId = (cursor == null) ? 0L : Long.parseLong(cursor);

        List<MemberMission> list = memberMissionRepository.findMissions(memberId, status, cursorId, size + 1); // size보다 1 크게 조회하여 hasNext 판단

        boolean hasNext = list.size() > size;
        List<MemberMission> result = hasNext ? list.subList(0, size) : list;

        String nextCursor = hasNext ? String.valueOf(result.getLast().getId()) : null;

        List<MissionResDTO.MissionDTO> missionDTOs = result.stream()
                .map(MissionConverter::toMissionDTO)
                .toList();

        return MissionConverter.toMissionListDTO(missionDTOs, nextCursor, hasNext);
    }

    public MissionResDTO.MissionCompleteResDTO completeMission(
            Long missionId
    ){
        // TODO : 미션 완료 구현
        return MissionConverter.toMissionCompleteResDTO(1L, MissionStatus.COMPLETED, 5, 10); // 임시
    }
}
