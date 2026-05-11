package com.example.jini_umc10th.domain.inquiry.entity;

import com.example.jini_umc10th.domain.inquiry.enums.InquiryStatus;
import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inquiry")
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "inquiry_title", nullable = false, length = 500)
    private String title;

    @Column(name = "inquiry_content", nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "inquiry_status", nullable = false)
    @Builder.Default
    private InquiryStatus status = InquiryStatus.답변대기중;

}
