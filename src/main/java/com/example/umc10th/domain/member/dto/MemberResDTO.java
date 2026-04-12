package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record signUp(
            Long id
    ) {}

    @Builder
    public record home(
            String location,
            Integer allMissionsCount,
            Integer successMissionsCount,
            List<MissionResDTO.HomeMission> missions
    ) {}
}
