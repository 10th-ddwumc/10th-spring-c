package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.StoreCategory;
import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    @Builder
    public record Success(
            Long id
    ) {}

    @Builder
    public record GetMissions(
            Long id,
            String storeName,
            Integer price,
            Integer point
    ) {}

    @Builder
    public record HomeMission(
            Long id,
            String storeName,
            Integer price,
            Integer point,
            StoreCategory category,
            LocalDate endDate
    ) {}
}
