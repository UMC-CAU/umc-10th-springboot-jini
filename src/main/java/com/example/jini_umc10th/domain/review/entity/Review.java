package com.example.jini_umc10th.domain.review.entity;

import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.mission.entity.Restaurant;
import com.example.jini_umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_review_id")
    private Long id;

    @Column(name = "rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "review_content", nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewPhoto> reviewPhotos;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewReply> reviewReplies;

}