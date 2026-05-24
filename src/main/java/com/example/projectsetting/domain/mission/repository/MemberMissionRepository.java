package com.example.projectsetting.domain.mission.repository;

import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.entity.mapping.MemberMission;
import com.example.projectsetting.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMember_IdAndStatusOrderByIdDesc(
            Long memberId,
            Status status,
            Pageable pageable
    );
}
