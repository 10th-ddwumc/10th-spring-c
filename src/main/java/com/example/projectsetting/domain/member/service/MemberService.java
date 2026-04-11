package com.example.projectsetting.domain.member.service;

import com.example.projectsetting.domain.member.dto.MemberReqDTO;
import com.example.projectsetting.domain.member.dto.MemberResDTO;
import com.example.projectsetting.global.apiPayload.ApiResponse;
import com.example.projectsetting.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

public class MemberService {
    public ApiResponse<MemberResDTO.Signup> signup(BaseSuccessCode code, MemberReqDTO.Signup dto) {
    }

    public ApiResponse<MemberResDTO.Dashboard> getDashboard(BaseSuccessCode code, String authorization) {
    }

    public ApiResponse<MemberResDTO.Mypage> getMypage(BaseSuccessCode code, String authorization) {
    }

}
