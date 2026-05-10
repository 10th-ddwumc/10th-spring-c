package com.example.projectsetting.domain.mission.entity.mapping;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.IN_PROGRESS;

    @Column(name = "verification_code", nullable = false)
    private String verificationCode;

    @Column(name = "left_date")
    private Long leftDate;

    @Column(name = "completed")
    private LocalDateTime completed;
}
