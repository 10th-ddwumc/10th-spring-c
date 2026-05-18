package com.example.umc10th.test;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.StoreCategory;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
테스트용 더미 데이터 저장
*/
@Component
@RequiredArgsConstructor
public class TestDataLoader {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @PostConstruct
    public void init() {

        //멤버 저장
        Member member = Member.builder()
                .name("레미")
                .email("yyl020626@naver.com")
                .phoneNumber("01022071917")
                .point(1000)
                .profileUrl("xxx")
                .birth(LocalDate.of(2002, 6, 26))
                .address(Address.GYEONGGI)
                .gender(Gender.FEMALE)
                .build();

        memberRepository.save(member);

        //가게 저장
        Store store1 = Store.builder()
                .name("성북구 최고맛집")
                .address("서울 성북구")
                .star(4.0)
                .category(StoreCategory.KOREAN)
                .build();

        Store store2 = Store.builder()
                .name("강남구 최고맛집")
                .address("서울 강남구")
                .star(4.1)
                .category(StoreCategory.JAPANESE)
                .build();

        storeRepository.save(store1);
        storeRepository.save(store2);

        //미션 저장
        Mission mission1 = Mission.builder()
                .price(10000)
                .endDate(LocalDate.now().plusDays(3))
                .point(1000)
                .store(store1)
                .conditional("10,000원 이상의 식사를 하세요!")
                .build();

        Mission mission2 = Mission.builder()
                .price(30000)
                .endDate(LocalDate.now().plusDays(7))
                .point(1500)
                .store(store1)
                .conditional("30,000원 이상의 식사를 하세요!")
                .build();

        Mission mission3 = Mission.builder()
                .price(20000)
                .endDate(LocalDate.now().plusDays(5))
                .point(1000)
                .store(store2)
                .conditional("20,000원 이상의 식사를 하세요!")
                .build();

        Mission mission4 = Mission.builder()
                .price(50000)
                .endDate(LocalDate.now().plusDays(10))
                .point(2000)
                .store(store2)
                .conditional("50,000원 이상의 식사를 하세요!")
                .build();

        missionRepository.save(mission1);
        missionRepository.save(mission2);
        missionRepository.save(mission3);
        missionRepository.save(mission4);

        //멤버 미션 저장
        MemberMission memberMission1 = MemberMission.builder()
                .member(member)
                .mission(mission1)
                .isPossible(true)
                .isSuccess(false)
                .build();

        MemberMission memberMission2 = MemberMission.builder()
                .member(member)
                .mission(mission2)
                .isPossible(true)
                .isSuccess(true)
                .build();

        MemberMission memberMission3 = MemberMission.builder()
                .member(member)
                .mission(mission3)
                .isPossible(true)
                .isSuccess(false)
                .build();

        memberMissionRepository.save(memberMission1);
        memberMissionRepository.save(memberMission2);
        memberMissionRepository.save(memberMission3);

    }
}
