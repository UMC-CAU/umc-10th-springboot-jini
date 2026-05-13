package com.example.jini_umc10th.domain.review.repository;

import com.example.jini_umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Id 순으로 회원의 리뷰 조회
    Slice<Review> findAllByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, Pageable pageable);

    // 별점 순으로 회원의 리뷰 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND  (r.rating < :ratingCursor OR (r.rating = :ratingCursor AND r.id < :idCursor)) ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findAllByMember_IdWithRatingCursorDesc(
            @Param("memberId") Long memberId,
            @Param("ratingCursor")BigDecimal ratingCursor,
            @Param("idCursor") long idCursor,
            Pageable pageable
    );

    // Id 순으로 회원의 리뷰 조회 - 첫페이지
    Slice<Review> findAllByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    // 별점 순으로 회원의 리뷰 조회 - 첫페이지
    Slice<Review> findAllByMember_IdOrderByRatingDescIdDesc(Long memberId, Pageable pageable);


}
