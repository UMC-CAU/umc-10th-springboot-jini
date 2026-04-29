package com.example.jini_umc10th.domain.member.repository;

import com.example.jini_umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    void deleteByName(String name);
}
