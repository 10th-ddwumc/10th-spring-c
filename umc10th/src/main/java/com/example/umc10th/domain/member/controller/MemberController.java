package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    // 마이페이지 조회 (JWT 인증 → @AuthenticationPrincipal)
    @GetMapping("/v1/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyInfo(
            @AuthenticationPrincipal Member member) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member.getId()));
    }

    // 회원가입 (Public API)
    @PostMapping("/v1/members/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResDTO.SignUpResultDto> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP, memberService.signUp(dto));
    }

    // 로그인 (Public API) → JWT 토큰 발급
    @PostMapping("/v1/members/login")
    public ApiResponse<MemberResDTO.LoginResultDto> login(
            @RequestBody @Valid MemberReqDTO.LoginDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(dto));
    }
}
