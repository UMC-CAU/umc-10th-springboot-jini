package com.example.jini_umc10th.domain.mission.service;

import com.example.jini_umc10th.domain.mission.converter.MissionConverter;
import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.entity.Mission;
import com.example.jini_umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.jini_umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 회원 미션 조회
    public MissionResDTO.Pagination<MissionResDTO.MissionDTO> getMemberMissions(
            Long memberId,
            MissionStatus status,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 회원의 미션들을 조회
        Page<MemberMission> missionList = memberMissionRepository.findAllByMember_IdAndStatus(memberId, status, pageRequest); // PageRequest는 Pageable을 구현한 객체.

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toMissionDTO).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    public MissionResDTO.MissionCompleteResDTO completeMission(
            Long missionId
    ){
        // TODO : 미션 완료 구현
        return MissionConverter.toMissionCompleteResDTO(1L, MissionStatus.COMPLETED, 5, 10); // 임시
    }
}
