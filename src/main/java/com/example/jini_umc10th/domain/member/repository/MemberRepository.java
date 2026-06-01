package com.example.jini_umc10th.domain.member.repository;

import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //void deleteByName(String name); // 추후 구현

    Optional<Member> findByEmailAddress(String emailAddress);

    Optional<Member> findBySocialProviderAndSocialUid(SocialProvider socialProvider, String socialUid);
}
