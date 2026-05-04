package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    //개별 미션 엔티티 -> MissionResDto.Mission
    public static MissionResDto.Mission toMissionResDto(MemberMission memberMission) {
        return MissionResDto.Mission.builder()
                .missionId(memberMission.getMission().getId())
                .title(memberMission.getMission().getStore().getName()+"에서")
                .content(memberMission.getMission().getContent())
                .reward(memberMission.getMission().getPoint())
                .status(memberMission.getStatus())
                .build();

    }

    //개별 미션 엔티티 -> MissionResDto.Mission
    public static MissionResDto.Mission toMissionResDto(Mission mission) {
        return MissionResDto.Mission.builder()
                .missionId(mission.getId())
                .title(mission.getStore().getName())
                .food(mission.getStore().getFood().getName().name())
                .content(mission.getContent())
                .reward(mission.getPoint())
                .build();

    }

    //Page 객체 -> MissionResDto.MissionList
    public static MissionResDto.MissionList toMemberMissionListDto(Page<MemberMission> missionPage){
        //리스트 변환
        List<MissionResDto.Mission> missionDtos=missionPage.getContent().stream()
                .map(mm -> MissionConverter.toMissionResDto(mm))
                .collect(Collectors.toList());

        Long nextLastId=missionDtos.isEmpty()?null:missionDtos.get(missionDtos.size()-1).missionId();

        return MissionResDto.MissionList.builder()
                .missionList(missionDtos)
                .nextLastId(nextLastId)
                .hasMore(missionPage.hasNext())
                .build();
    }

    //Page 객체 -> MissionResDto.MissionList
    public static MissionResDto.MissionList toMissionListDto(Page<Mission> missionPage, Integer completedCount){
        //리스트 변환
        List<MissionResDto.Mission> missionDtos=missionPage.getContent().stream()
                .map(m -> MissionConverter.toMissionResDto(m))
                .collect(Collectors.toList());

        Long nextLastId=missionDtos.isEmpty()?null:missionDtos.get(missionDtos.size()-1).missionId();

        return MissionResDto.MissionList.builder()
                .missionList(missionDtos)
                .nextLastId(nextLastId)
                .hasMore(missionPage.hasNext())
                .completedCount(completedCount)
                .build();
    }


}
