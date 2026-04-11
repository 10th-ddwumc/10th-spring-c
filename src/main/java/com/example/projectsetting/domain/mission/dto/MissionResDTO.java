package com.example.projectsetting.domain.mission.dto;

public class MissionResDTO {

    public record Success(
        Long missionId,
        String status
    ){}

    public record Mission(
            Long missionId,
            String store,
            Long price,
            Long reward,
            String status
    ){}
}
