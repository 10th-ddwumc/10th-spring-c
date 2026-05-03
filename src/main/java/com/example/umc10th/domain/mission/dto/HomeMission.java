package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.StoreCategory;
import lombok.Builder;

import java.time.LocalDate;

/**
DTO Projection을 위해 클래스 분리
 **/

@Builder
public record HomeMission(Long id,
                          String storeName,
                          Integer price,
                          Integer point,
                          StoreCategory category,
                          LocalDate endDate) {
}
