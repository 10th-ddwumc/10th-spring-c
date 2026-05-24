package com.example.projectsetting.domain.mission.dto;

import com.example.projectsetting.domain.mission.enums.Status;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record Success(
        Long missionId,
        String status
    ){}

    @Builder
    public record Mission(
            Long missionId,
            String store,
            Long price,
            Long reward,
            Status status
    ){}

    @Builder
    public record MissionCard(
            Long missionId,
            String store,
            String category,
            Integer point,
            Integer reward,
            Integer price,
            String dDay
    ){}

    //가게 내 미션 조회
    @Builder
    public record GetMission(
      Long missionId,
      Integer point,
      String conditional
    ){}

    //오프셋 기반 페이지 네이션
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

    //커서 기반 페이지네이션
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}


}
