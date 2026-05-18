package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.*;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.HomeMission;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;

    private final PasswordEncoder passwordEncoder;

    //마이페이지 조회
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto)
    {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    //회원가입
    public MemberResDTO.signUp signUp(MemberReqDTO.signUp dto) {

        // 이메일 중복 검사
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.ALREADY_EXISTS_EMAIL);
        }

        // 필수 약관 조회
        List<Term> requiredTerms = termRepository.findByRequiredTrue();

        // 필수 약관 동의 여부 검사
        for (Term required : requiredTerms) {

            boolean agreed = dto.terms().stream()
                    .anyMatch(t ->
                            t.termId().equals(required.getId())
                                    && Boolean.TRUE.equals(t.agreed()));

            if (!agreed) {
                throw new MemberException(MemberErrorCode.REQUIRED_TERMS_NOT_AGREED);
            }
        }

        // 음식 카테고리 조회
        List<Food> foods = foodRepository.findAllById(dto.foods());

        if (foods.size() != dto.foods().size()) {
            throw new MemberException(MemberErrorCode.INVALID_FOOD);
        }

        // 회원 저장
        Member member = Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        memberRepository.save(member);

        // 회원-음식 저장
        List<MemberFood> memberFoods = foods.stream()
                .map(food -> MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build())
                .toList();

        memberFoodRepository.saveAll(memberFoods);

        // 약관 ID 목록 추출
        List<Long> termsIds = dto.terms().stream()
                .map(MemberReqDTO.TermsAgreed::termId)
                .toList();

        // 약관 조회
        List<Term> termsList = termRepository.findAllById(termsIds);

        Map<Long, Term> termsMap = termsList.stream()
                .collect(Collectors.toMap(
                        Term::getId,
                        Function.identity()
                ));

        // 회원-약관 저장
        List<MemberTerm> memberTerms = dto.terms().stream()
                .map(t -> MemberTerm.builder()
                        .member(member)
                        .term(termsMap.get(t.termId()))
                        .agreed(t.agreed())
                        .build()
                )
                .toList();

        memberTermRepository.saveAll(memberTerms);

        return MemberConverter.toSignUp(member);
    }

    //홈화면 조회
    public MemberResDTO.home home(
            Long memberId,
            String location,
            Long lastId,
            int pageSize)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        //1. 도전 가능 미션 가져오기
        List<HomeMission> missions = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, pageSize);

        if (lastId != null) {
            missions = missionRepository.findByLocationWithCursor(memberId, location, lastId, pageable);
        } else {
            missions = missionRepository.findByLocationWithoutCursor(memberId, location, pageable);
        }

        //2. 선택된 지역의 전체 미션 수 가져오기
        Integer allMissions = memberMissionRepository.countAllMissionsByLocation(memberId, location);

        //3. 선택된 지역의 성공 완료 미션 수 가져오기
        Integer successMission = memberMissionRepository.countSuccessMissionsByLocation(memberId, location);

        return MemberConverter.toHome(location, allMissions, successMission, missions);
    }
}
