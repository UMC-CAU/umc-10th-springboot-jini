package com.example.jini_umc10th.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terms_of_service")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_of_service_id")
    private Long id;

    @Column(name = "terms_title", nullable = false, length = 50)
    private String termsTitle;

    @Column(name = "terms_content", nullable = false, length = 500)
    private String termsContent;

    @Column(name = "is_terms_required", nullable = false)
    private boolean isTermsRequired;

    @Column(name = "terms_version", nullable = false, length = 11)
    private String termsVersion;
}
