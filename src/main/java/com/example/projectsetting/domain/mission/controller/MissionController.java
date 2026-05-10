package com.example.projectsetting.domain.mission.controller;

import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.exception.code.MemberSuccessCode;
import com.example.projectsetting.domain.mission.dto.MissionReqDTO;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.domain.mission.enums.Status;
import com.example.projectsetting.domain.mission.exception.code.MissionSuccessCode;
import com.example.projectsetting.domain.mission.service.MissionService;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import com.example.projectsetting.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 생성
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody  @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.creeteMission(storeId, dto));
    }

    //가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        MissionResDTO.Pagination<MissionResDTO.GetMission> result =
                missionService.getStoreMissions(storeId,pageSize,cursor,query);

        return ApiResponse.onSuccess(code,result);
    }

    //미션 목록 조회
    @GetMapping("/users/{userId}/missions")
    public ApiResponse<List<MissionResDTO.Mission>> getMissions(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authorization,
            @RequestParam Status status
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        List<MissionResDTO.Mission> result =
                missionService.getMissions(userId,authorization,status);
        return ApiResponse.onSuccess(code, result);
    }

    //미션 성공 누르기
    @PatchMapping("missions/{missionId}")
        public ApiResponse<MissionResDTO.Success> success(
            @RequestBody MissionReqDTO.Success dto,
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return missionService.success(code,dto,authorization,missionId);
    }

    //내가 진행중인 미션 조회
    @PostMapping("/users/missions/in-progress")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.Mission>> getInProgressMissions(
        @RequestBody MissionReqDTO.InProgress dto,
        @RequestParam Integer pageSize,
        @RequestParam Integer pageNumber,
        @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getInProgressMissions(dto, pageSize, pageNumber, sort));
    }
}
