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

    @PostMapping
    public ApiResponse<MemberResDTO.Signup> signup(
            @RequestBody MemberReqDTO.Signup dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return memberService.signup(code,dto);
    }

    @GetMapping("/dashboard")
        public ApiResponse<MemberResDTO.Dashboard> getDashboard(
                @RequestHeader("Authorization") String authorization
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return memberService.getDashboard(code,authorization);
    }

    @GetMapping("/me")
        public ApiResponse<MemberResDTO.Mypage> getMypage(
                @RequestHeader("Authorization") String authorization
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return memberService.getMypage(code, authorization);
    }

}
