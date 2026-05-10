package com.example.projectsetting.domain.member.controller;

import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.domain.member.exception.code.MemberSuccessCode;
import com.example.projectsetting.domain.member.service.MemberService;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping
    public ApiResponse<MemberResDTO.Signup> signup(
            @RequestBody MemberReqDTO.Signup dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return memberService.signup(code,dto);
    }

    //홈화면
    @GetMapping("/dashboard")
        public ApiResponse<MemberResDTO.Dashboard> getDashboard(
                @RequestHeader("Authorization") String authorization
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        MemberResDTO.Dashboard result = memberService.getDashboard(authorization);
        return ApiResponse.onSuccess(code,result);
    }

    //마이페이지
    @GetMapping("/me")
        public ApiResponse<MemberResDTO.Mypage> getMypage(
                @RequestHeader("Authorization") String authorization
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        MemberResDTO.Mypage result = memberService.getMypage(authorization);
        return ApiResponse.onSuccess(code, result);
    }

}
