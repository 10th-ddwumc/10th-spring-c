package com.example.projectsetting.domain.mission.service;

import com.example.projectsetting.domain.mission.dto.MissionReqDTO;
import com.example.projectsetting.domain.mission.dto.MissionResDTO;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

public class MissionService {

    public ApiResponse<MissionResDTO.Success> success(BaseSuccessCode code, MissionReqDTO.Success dto, String authorization, Long missionId) {
    }

    public ApiResponse<List<MissionResDTO.Mission>> getMissions(BaseSuccessCode code, Long userId, String authorization, String status) {
    }
}
