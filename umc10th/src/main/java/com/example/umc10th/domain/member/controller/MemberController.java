package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 조회
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(@RequestBody MemberReqDTO.GetInfo dto) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원가입 (Public API)
    @PostMapping("/v1/members/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResDTO.SignUpResultDto> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP, memberService.signUp(dto));
    }
}