package com.example.jini_umc10th.domain.member.entity;

import com.example.jini_umc10th.domain.member.enums.Gender;
import com.example.jini_umc10th.domain.member.enums.SocialProvider;
import com.example.jini_umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)  // ← 이것도 있어야 함
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialProvider socialProvider;

    @Column(name = "social_uid", nullable = false, length = 50)
    private String socialUid;

    @Column(nullable = false)
    @Builder.Default
    private int point = 0;

    @Column(name = "email_address", nullable = false, length = 50)
    private String emailAddress;

    @Column(name = "is_email_verified", nullable = false)
    @Builder.Default
    private boolean isEmailVerified = false;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "is_phone_verified", nullable = false)
    @Builder.Default
    private boolean isPhoneVerified = false;




}
