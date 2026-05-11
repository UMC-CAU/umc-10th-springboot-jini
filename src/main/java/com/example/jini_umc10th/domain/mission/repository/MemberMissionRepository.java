package com.example.jini_umc10th.domain.mission.repository;

import com.example.jini_umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.jini_umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 커서 기반으로 작성했던 회원의 미션 조회
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status AND mm.id > :cursorId ORDER BY mm.id ASC LIMIT :size")
    List<MemberMission> findMemberMission(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            @Param("cursorId") Long cursorId,
            @Param("size") int size
    );

    // 오프셋 기반으로 작성한 회원의 미션 조회
    Page<MemberMission> findAllByMember_IdAndStatus(Long memberId, MissionStatus Status, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status AND mm.id > :cursorId AND mm.mission.restaurant.area.id = :regionId ORDER BY mm.id ASC LIMIT :size")
    List<MemberMission> findMissionsInArea(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            @Param("cursorId") Long cursorId,
            @Param("regionId") Long regionId,
            @Param("size") int size
    );

    @Query("SELECT COUNT(mm) FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status AND mm.mission.restaurant.area.id = :regionId")
    int countMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            @Param("regionId") Long regionId
    );
}
