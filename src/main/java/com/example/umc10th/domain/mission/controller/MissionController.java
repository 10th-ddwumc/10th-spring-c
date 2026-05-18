package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.pagination.PaginationResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/api/{memberId}/missions")
    public ApiResponse<PaginationResDTO.OffsetPagination<MissionResDTO.GetMissions>> getMissions(
            @RequestParam Boolean isSuccess,
            @RequestBody @Valid MissionReqDTO.getMyMissions dto,
            /*@PathVariable("memberId") Long memberId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastEndDate, //페이징 커서
            @RequestParam(required = false) Long lastId, //페이징 커서*/
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionService.getMissions(isSuccess, dto, pageSize, pageNumber, sort));
    }

    //가게 내 미션 생성
    @PostMapping("/api/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    //가게 내 미션 조회
    @GetMapping("/api/stores/{storeId}/missions")
    public ApiResponse<PaginationResDTO.CursorPagination<MissionResDTO.GetMission>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getStoreMissions(storeId, pageSize, cursor, query));
    }
}
