package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    //미션 성공 누르기
    @PatchMapping("/api/{memberId}/missions/{missionId}/success")
    public ApiResponse<MissionResDTO.Success> success(
            @PathVariable("missionId") Long missionId,
            @PathVariable("memberId") Long memberId
    ) {
        BaseSuccessCode code = MissionSuccessCode.SUCCESS_OK;
        return ApiResponse.onSuccess(code, missionService.success(missionId, memberId));
    }

    //완료/미완료 미션 조회
    @GetMapping("/api/{memberId}/missions")
    public ApiResponse<List<MissionResDTO.GetMissions>> getMissions(
            @RequestParam Boolean isSuccess,
            @PathVariable("memberId") Long memberId
    ) {
        BaseSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionService.getMissions(isSuccess, memberId));
    }
}
