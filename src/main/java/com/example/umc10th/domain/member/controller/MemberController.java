package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //마이페이지 조회
    @PostMapping("/api/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody @Valid MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
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
            @PathVariable("memberId") Long memberId,
            @RequestParam("location") String location,
            @RequestParam(required = false) Long lastId, //미션 조회용 커서
            @RequestParam(defaultValue = "10") int pageSize

    ) {
        BaseSuccessCode code = MemberSuccessCode.SUCCESS_HOME;
        return ApiResponse.onSuccess(code, memberService.home(memberId, location, lastId, pageSize));
    }
}
