package com.example.projectsetting.domain.mission.converter;

import com.example.projectsetting.domain.mission.dto.MissionReqDTO;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.entity.Store;
import com.example.projectsetting.domain.mission.enums.Status;

import java.util.List;

import static java.util.stream.DoubleStream.builder;

public class MissionConverter {

    //mission 엔티티 dto로 변환
    public static MissionResDTO.Mission toResultMission(Mission mission){
        return MissionResDTO.Mission.builder()
                .missionId(mission.getId())
                .store(mission.getStore().getName())
                .price(mission.getPrice())
                .reward(mission.getReward())
                .status(mission.getStatus())
                .build();
    }

    //가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    //가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 오프셋페이지네이션 툴 생성
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    // 커서페이지네이션 툴 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    public static MissionResDTO.Mission toGetInProgressMission(Mission mission) {
        return MissionResDTO.Mission.builder()
                .missionId(mission.getId())
                .store(mission.getStore().getName())
                .price(mission.getPrice())
                .reward(mission.getReward())
                .status(Status.IN_PROGRESS)
                .build();
    }


}
