package com.example.jini_umc10th.domain.mission.entity;

import com.example.jini_umc10th.domain.mission.enums.AreaName;
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
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Column(name = "area_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private AreaName name;
}
