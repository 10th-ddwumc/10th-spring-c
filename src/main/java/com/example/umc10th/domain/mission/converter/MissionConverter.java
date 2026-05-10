package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    //완료/미완료 미션 조회
    public static MissionResDTO.GetMissions toGetMissions(MemberMission myMission) {
        return MissionResDTO.GetMissions.builder()
                .id(myMission.getId())
                .price(myMission.getMission().getPrice())
                .point(myMission.getMission().getPoint())
                .storeName(myMission.getMission().getStore().getName())
                .build();
    }

    //가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .endDate(dto.deadline())
                .point(dto.point())
                .build();
    }

    //가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }
}
