package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MissionController {

    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateMissionResultDto> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMissionDto request) {
        return null;
    }

    @PostMapping("/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeMissionResultDto> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.ChallengeMissionDto request) {
        return null;
    }
}
