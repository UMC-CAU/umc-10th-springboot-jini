package com.example.jini_umc10th.domain.member.entity.mapping;

import com.example.jini_umc10th.domain.member.entity.Member;
import com.example.jini_umc10th.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_terms_of_service")
@EntityListeners(AuditingEntityListener.class)
public class MemberTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member-terms_of_service_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_of_service_id", nullable = false)
    private Term term;

    @Column(name = "is_term_agreed", nullable = false)
    private Boolean isAgreed;

    @CreatedDate
    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;

}
