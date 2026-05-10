package com.example.projectsetting.domain.mission.repository;

import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {

    //미션 목록 조회
    @Query(value = "SELECT m.* " +
            "FROM mission m " +
            "JOIN member_mission mm ON m.id = mm.mission_id " +
            "WHERE mm.status = :status and mm.member_id = :memberId " +
            "ORDER BY m.id desc limit 10 offset 0 ", nativeQuery = true)
    List<Mission> findMissionByMemberIdAndStatus(
            @Param("status") String status,
            @Param("memberId") Long memberId
    );


    //위치기반 도전가능 미션 목록 조회
    @Query(value = "SELECT m.* " +
            "FROM mission m " +
            "JOIN store s ON m.store_id = s.id " +
            "JOIN location l ON s.location_id = l.id " +
            "WHERE l.name = :location and " +
            "AND NOT EXISTS ( " +
            "    SELECT 1 " +
            "    FROM member_mission mm " +
            "    WHERE mm.member_id = :memberId " +
            "    AND mm.mission_id = m.id " +
            ") " +
            "ORDER BY m.id desc limit 10 offset 0", nativeQuery = true)
    List<Mission> findMissionByLocation(
            @Param("location") String location,
            @Param("memberId") Long memberId
    );

    //가게 내 미션들 조회
    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    Slice<Mission> findMissionByStore_IdAndIdLessThanOrderByIdDesc(
            Long storeId,
            Long id,
            Pageable pageable
    );

    Slice<Mission> findMissionsByStore_IdOrderByIdDesc(
            Long storeId,
            Pageable pageable
    );


    //진행중인 미션들 조회
    Page<Mission> findMissionByStatusOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
