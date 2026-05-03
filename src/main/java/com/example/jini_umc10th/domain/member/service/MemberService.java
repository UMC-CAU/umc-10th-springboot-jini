package com.example.jini_umc10th.domain.member.service;

import com.example.jini_umc10th.domain.member.converter.MemberConverter;
import com.example.jini_umc10th.domain.member.dto.MemberReqDTO;
import com.example.jini_umc10th.domain.member.dto.MemberResDTO;
import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import com.example.jini_umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.HomeResDTO getHome(Long memberId, String cursor, String regionId, int size) {
        // TODO : memberId로 해당 회원 조회 후 홈화면 데이터 반환
        return MemberResDTO.HomeResDTO.builder()
                .missions(List.of())
                .nextCursor(null)
                .hasNext(false)
                .build();
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
}