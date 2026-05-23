package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Integer price;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "point")
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "conditional")
    private String conditional;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();
}
