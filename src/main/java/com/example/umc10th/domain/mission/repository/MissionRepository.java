package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.HomeMission;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    //커서O
    @Query(
            "select m from Mission m " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and mm.isSuccess = :isSuccess " +
                    "and (" +
                    "m.endDate < :lastEndDate " +
                    "or (m.endDate = :lastEndDate and m.id < :lastId) " +
                    ") " +
                    "order by m.endDate desc, m.id desc"
    )
    List<Mission> findByIsSuccessWithCursor(Long memberId,
                                            Boolean isSuccess,
                                            LocalDate lastEndDate,
                                            Long lastId,
                                            Pageable pageable);

    //커서X(최초 조회)
    @Query(
            "select m from Mission m " +
                    "join m.memberMissions mm " +
                    "where mm.member.id = :memberId " +
                    "and mm.isSuccess = :isSuccess " +
                    "order by m.endDate desc, m.id desc"
    )
    List<Mission> findByIsSuccessWithoutCursor(Long memberId,
                                               Boolean isSuccess,
                                               Pageable pageable);

    //홈화면 미션 조회용 - 선택된 지역에서 도전 가능 미션 조회
    //커서O
    @Query(
            "select new com.example.umc10th.domain.mission.dto.HomeMission(m.id, s.name, m.price, m.point, s.category, m.endDate) " +
                    "from Mission m " +
                    "join m.memberMissions mm " +
                    "join m.store s " +
                    "where mm.member.id = :memberId " +
                    "and mm.isPossible = true " +
                    "and s.location = :location " +
                    "and m.endDate >= CURRENT_DATE " +
                    "and mm.id < :lastId " +
                    "order by mm.id desc"
    )
    List<HomeMission> findByLocationWithCursor(Long memberId,
                                               String location,
                                               Long lastId,
                                               Pageable pageable);

    //홈화면 미션 조회용 - 선택된 지역에서 도전 가능 미션 조회
    //커서X
    @Query(
            "select new com.example.umc10th.domain.mission.dto.HomeMission(m.id, s.name, m.price, m.point, s.category, m.endDate) " +
                    "from Mission m " +
                    "join m.memberMissions mm " +
                    "join m.store s " +
                    "where mm.member.id = :memberId " +
                    "and mm.isPossible = true " +
                    "and s.location = :location " +
                    "and m.endDate >= CURRENT_DATE " +
                    "order by mm.id desc"
    )
    List<HomeMission> findByLocationWithoutCursor(Long memberId,
                                                    String location,
                                                    Pageable pageable);

    Slice<Mission> findMissionsByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, Long idIsLessThan, Pageable pageable);

    Slice<Mission> findMissionsByStore_IdOrderByIdDesc(Long storeId, Pageable pageable);
}
