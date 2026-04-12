package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //마이페이지
    @PostMapping("/api/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInto(dto));
    }

    //회원가입
    @PostMapping("/auth/users/signup")
    public ApiResponse<MemberResDTO.signUp> signUp(
            @RequestBody MemberReqDTO.signUp dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.signUp(dto));
    }

    //홈화면 조회
    @GetMapping("/api/{memberId}/home")
    public ApiResponse<MemberResDTO.home> home(
            @PathVariable("memberId") Long memberId
    ) {
        BaseSuccessCode code = MemberSuccessCode.SUCCESS_HOME;
        return ApiResponse.onSuccess(code, memberService.home(memberId));
    }
}
