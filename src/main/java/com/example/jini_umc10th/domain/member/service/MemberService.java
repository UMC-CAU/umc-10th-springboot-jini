package com.example.jini_umc10th.domain.member.service;

import com.example.jini_umc10th.domain.member.converter.MemberConverter;
import com.example.jini_umc10th.domain.member.dto.MemberReqDTO;
import com.example.jini_umc10th.domain.member.dto.MemberResDTO;
import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import com.example.jini_umc10th.domain.member.repository.MemberRepository;
import com.example.jini_umc10th.domain.mission.converter.MissionConverter;
import com.example.jini_umc10th.domain.mission.dto.MissionResDTO;
import com.example.jini_umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import com.example.jini_umc10th.domain.mission.exception.MissionException;
import com.example.jini_umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.jini_umc10th.domain.mission.repository.AreaRepository;
import com.example.jini_umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jini_umc10th.domain.member.exception.MemberException;
import com.example.jini_umc10th.domain.member.exception.code.MemberErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final AreaRepository areaRepository;

    public MemberResDTO.homeResDTO getHome(Long memberId, String cursor, Long regionId, int size) {

        Long cursorId = (cursor == null) ? 0L : Long.parseLong(cursor);

        List<MemberMission> list = memberMissionRepository.findMissionsInArea(memberId, MissionStatus.AVAILABLE, cursorId, regionId,size + 1);
        String areaName = areaRepository.findById(regionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.AREA_NOT_FOUND))
                .getName()
                .name();

        boolean hasNext = list.size() > size;
        List<MemberMission> result = hasNext ? list.subList(0, size) : list;

        String nextCursor = hasNext ? String.valueOf(result.getLast().getId()) : null;

        List<MissionResDTO.MissionDTO> missionDTOs = result.stream()
                .map(MissionConverter::toMissionDTO)
                .toList();

        int completedNum = memberMissionRepository.countMissions(memberId, MissionStatus.COMPLETED, regionId) % 10; // 10개 단위로 끊어 포인트가 지급되는 사이클이라고 가정하고, 이번 사이클에 완료한 미션의 개수 반환

        return MemberConverter.toHomeResDTO(missionDTOs, nextCursor, hasNext, areaName, completedNum);
    }

    @Transactional
    public MemberResDTO.signUpResDTO createUser(
        MemberReqDTO.signUpReqDTO dto
    ){
        Member member = Member.builder()
                .id(1L) // 임시
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .emailAddress("temp@google.com")   // 임시
                .socialProvider(SocialProvider.GOOGLE) // 임시
                .socialUid("1234")          // 임시
                .build();

        Member saved = memberRepository.save(member);

        return MemberConverter.toSignUpResDTO(saved.getId(), dto.name(), saved.getCreatedAt());
    }

    public MemberResDTO.loginResDTO login(
            MemberReqDTO.loginReqDTO dto
    ){
        // TODO : 로그인 기능 구현
        return MemberConverter.toLoginResDTO(dto.socialProvider(), dto.socialUid());
    }

    public MemberResDTO.profileResDTO profile(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toProfileResDTO(
                member.getName(),
                member.getEmailAddress(),
                member.isEmailVerified(),
                member.getPhoneNumber(),
                member.isPhoneVerified(),
                member.getPoint()
        );
    }
}