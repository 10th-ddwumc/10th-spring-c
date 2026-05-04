package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면
    @GetMapping("/summary")
    public ApiResponse<MissionResDto.MissionList> missionSummary(
            @AuthenticationPrincipal Member member,
            @ModelAttribute MissionReqDto.MissionSummary request
    ){
        MissionResDto.MissionList result=missionService.missionSummary(member, request);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_SUMMARIZED, result);
    }

    // 미션 목록 조회
    @GetMapping("")
    public ApiResponse<MissionResDto.MissionList> getMissionList(
            @AuthenticationPrincipal Member member,
            @ModelAttribute MissionReqDto.GetMissionList request
    ){
        MissionResDto.MissionList result=missionService.getMissionList(member, request);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CHECKED, result);
    }

    //미션 성공 누르기
    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDto.CompleteMission> completeMission(
            @AuthenticationPrincipal Member member,
            @PathVariable Long memberMissionId
    ){
        MissionResDto.CompleteMission result=missionService.completeMission(member, memberMissionId);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, result);
    }
}