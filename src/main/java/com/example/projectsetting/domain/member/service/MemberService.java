package com.example.projectsetting.domain.member.service;

import com.example.projectsetting.domain.member.converter.MemberConverter;
import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.MemberRepository;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ApiResponse<MemberResDTO.Signup> signup(BaseSuccessCode code, MemberReqDTO.Signup dto) {

        return null;
    }

    public ApiResponse<MemberResDTO.Dashboard> getDashboard(BaseSuccessCode code, String authorization) {

        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return ApiResponse.onSuccess(code, MemberConverter.toDashboard(member));
    }

    public ApiResponse<MemberResDTO.Mypage> getMypage(BaseSuccessCode code, String authorization) {

        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return ApiResponse.onSuccess(code, MemberConverter.toMypage(member));
    }

}
