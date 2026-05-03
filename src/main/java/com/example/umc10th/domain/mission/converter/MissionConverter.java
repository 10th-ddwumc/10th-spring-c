package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

public class MissionConverter {

    //완료/미완료 미션 조회
    public static MissionResDTO.GetMissions toGetMissions(Mission myMission) {
        return MissionResDTO.GetMissions.builder()
                .id(myMission.getId())
                .price(myMission.getPrice())
                .point(myMission.getPoint())
                .storeName(myMission.getStore().getName())
                .build();
    }
}
