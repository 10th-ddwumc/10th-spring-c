package com.example.projectsetting.domain.member.service;

import com.example.projectsetting.domain.member.converter.MemberConverter;
import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Food;
import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.entity.Term;
import com.example.projectsetting.domain.member.entity.mapping.MemberFood;
import com.example.projectsetting.domain.member.entity.mapping.MemberTerm;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.*;
import com.example.projectsetting.domain.mission.entity.Mission;
import com.example.projectsetting.domain.mission.repository.MissionRepository;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import com.example.projectsetting.global.security.entity.AuthMember;
import com.example.projectsetting.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final PasswordEncoder passwordEncoder;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final JwtUtil jwtUtil;

    public ApiResponse<MemberResDTO.Signup> signup(BaseSuccessCode code, MemberReqDTO.Signup dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toSignup(dto, encodedPassword);
        Member savedmember = memberRepository.save(member);

        List<Term> terms = termRepository.findAllByNameIn(dto.agreedTerms());
        List<MemberTerm> memberTerms = terms.stream()
                .map(term -> MemberConverter.toMemberTerm(savedmember, term))
                .toList();
        memberTermRepository.saveAll(memberTerms);

        List<Food> foodCategorys = foodRepository.findAllByNameIn(dto.favoriteFoods());
        List<MemberFood> memberFoods = foodCategorys.stream()
                .map(food -> MemberConverter.toMemberFood(savedmember, food))
                .toList();
        memberFoodRepository.saveAll(memberFoods);

        return ApiResponse.onSuccess(code,MemberConverter.toResultSignup(savedmember));
    }

    public ApiResponse<MemberResDTO.Login> login(BaseSuccessCode code, MemberReqDTO.Login dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(()-> new MemberException(MemberErrorCode.NOT_FOUND));

        if(!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.NOT_FOUND);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return ApiResponse.onSuccess(code, MemberConverter.toLogin(accessToken));
    }

    public MemberResDTO.Dashboard getDashboard(String authorization) {

        //임시값
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.NOT_FOUND));
        String location = member.getAddress();

        List<Mission> missions = missionRepository.findMissionByLocation(location, memberId);

        return MemberConverter.toResultDashboard(member,missions);
    }


    public MemberResDTO.Mypage getMypage(AuthMember member) {

        return MemberConverter.toResultMypage(member.getMember());
    }





}

