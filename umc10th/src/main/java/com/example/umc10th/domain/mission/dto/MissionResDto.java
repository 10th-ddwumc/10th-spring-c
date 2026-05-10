package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDto {

    // 홈 화면+미션 목록 조회용 미션 목록
    @Builder
    public record MissionList(
            List<Mission> missionList,
            Long nextLastId,
            Boolean hasMore,
            Integer completedCount
    ){}

    // 미션
    @Builder
    public record Mission(
            Long missionId,
            String title,
            String content,
            Integer reward,
            String status,
            String food
    ){}

    // 미션 성공 누르기
    @Builder
    public record CompleteMission(
            Long missionId,
            LocalDateTime updatedAt
    ){}

    // 진행중인 미션 조회 (오프셋 기반 페이지네이션 응답)
    @Builder
    public record MissionOffsetList(
            List<Mission> missionList,
            int currentPage,
            int totalPages,
            long totalCount,
            boolean hasMore
    ){}

}