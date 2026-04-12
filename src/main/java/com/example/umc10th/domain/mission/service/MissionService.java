package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    public MissionResDTO.Success success(Long missionId, Long memberId) {
        return null;
    }

    public List<MissionResDTO.GetMissions> getMissions(Boolean isSuccess, Long memberId) {
        return null;
    }
}
