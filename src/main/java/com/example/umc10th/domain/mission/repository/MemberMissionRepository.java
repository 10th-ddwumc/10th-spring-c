package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    //선택된 지역의 전체 미션 수
    @Query(
            "select count(distinct mm.id) " +
                    "from MemberMission mm " +
                    "join mm.mission m " +
                    "join m.store s " +
                    "where mm.member.id = :memberId " +
                    "and s.location = :location"
    )
    Integer countAllMissionsByLocation(Long memberId, String location);

    //선택된 지역의 성공 완료 미션 수
    @Query(
            "select count(distinct mm.id) " +
                    "from MemberMission mm " +
                    "join mm.mission m " +
                    "join m.store s " +
                    "where mm.member.id = :memberId " +
                    "and s.location = :location " +
                    "and mm.isPossible = true " +
                    "and mm.isSuccess = true"
    )
    Integer countSuccessMissionsByLocation(Long memberId, String location);
}
