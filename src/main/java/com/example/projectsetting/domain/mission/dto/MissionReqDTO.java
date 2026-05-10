package com.example.projectsetting.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    public record Success(
           String title,
           String status
    ){}

    //가게 미션 생성
    public record CreateMission(
            @NotNull(message =  "마갑기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}

    //진행중인 미션 조회
    public record  InProgress(
        Long userId
    ){}
}
