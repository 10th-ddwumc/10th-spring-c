package com.example.projectsetting.domain.mission.controller;

import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.exception.code.MemberSuccessCode;
import com.example.projectsetting.domain.mission.dto.MissionReqDTO;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.exception.code.MissionSuccessCode;
import com.example.projectsetting.domain.mission.service.MissionService;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import com.example.projectsetting.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/users/{userId}/missions")
    public ApiResponse<List<MissionResDTO.Mission>> getMissions(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authorization,
            @RequestParam String status
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return missionService.getMissions(code, userId, authorization, status);
    }

    @PatchMapping("missions/{missionId}")
        public ApiResponse<MissionResDTO.Success> success(
            @RequestBody MissionReqDTO.Success dto,
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return missionService.success(code,dto,authorization,missionId);
    }


}
