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
import com.example.jini_umc10th.global.security.entity.AuthMember;
import com.example.jini_umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .emailAddress(dto.emailAddress())
                .socialProvider(SocialProvider.GOOGLE) // 임시
                .socialUid("1234")          // 임시
                .password(passwordEncoder.encode(dto.password())) // 폼 로그인 기반 비밀번호. BCrypt로 솔트처리
                .build();

        Member saved = memberRepository.save(member);
        // 회원가입 후 바로 JWT 발급
        String accessToken = jwtUtil.createAccessToken(new AuthMember(saved));

        return MemberConverter.toSignUpResDTO(saved.getId(), dto.name(), saved.getCreatedAt(), accessToken);
    }

    // JWT 토큰 기반 로그인
    public MemberResDTO.loginResDTO login(
            MemberReqDTO.loginReqDTO dto
    ) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmailAddress(dto.emailAddress())
                .orElseThrow(() -> new
                        MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // 3. JWT 발급
        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toLoginResDTO(accessToken);
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