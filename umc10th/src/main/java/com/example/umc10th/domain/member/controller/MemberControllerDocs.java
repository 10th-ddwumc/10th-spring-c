package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Member API", description = "회원 관련 API (회원가입, 로그인, 마이페이지)")
public interface MemberControllerDocs {

    @Operation(
            summary = "마이페이지 조회 By 지요선",
            description = "JWT 토큰으로 인증된 사용자의 마이페이지 정보를 조회합니다.\n\n" +
                    "**인증 필요**: `Authorization: Bearer <token>` 헤더를 포함해야 합니다.",
            security = @SecurityRequirement(name = "JWT TOKEN")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "마이페이지 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "isSuccess": true,
                                      "code": "MEMBER200_1",
                                      "message": "성공적으로 유저를 조회했습니다.",
                                      "result": {
                                        "name": "지요선",
                                        "profileUrl": null,
                                        "email": "jio@example.com",
                                        "phoneNumber": "010-1234-5678",
                                        "phoneNumberVerified": false,
                                        "point": 0
                                      }
                                    }
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 없음 or 만료)")
    })
    com.example.umc10th.global.apiPayload.ApiResponse<MemberResDTO.GetInfo> getMyInfo(
            @AuthenticationPrincipal Member member);

    @Operation(
            summary = "회원가입 By 지요선",
            description = "이메일과 비밀번호로 새 계정을 생성합니다.\n\n" +
                    "- 비밀번호는 **최소 8자** 이상이어야 합니다.\n" +
                    "- 이미 가입된 이메일은 사용할 수 없습니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "회원가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "isSuccess": true,
                                      "code": "MEMBER201_1",
                                      "message": "회원가입이 완료되었습니다.",
                                      "result": {
                                        "memberId": 1,
                                        "email": "jio@example.com",
                                        "createdAt": "2026-05-24T23:00:00"
                                      }
                                    }
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "입력값 검증 실패"),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일")
    })
    com.example.umc10th.global.apiPayload.ApiResponse<MemberResDTO.SignUpResultDto> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDto dto);

    @Operation(
            summary = "로그인 By 지요선",
            description = "이메일과 비밀번호로 로그인하여 JWT 액세스 토큰을 발급받습니다.\n\n" +
                    "발급된 `accessToken`을 Swagger 우측 상단 **Authorize** 버튼에 입력하면 인증이 필요한 API를 테스트할 수 있습니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공 — JWT 토큰 반환",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "isSuccess": true,
                                      "code": "MEMBER200_1",
                                      "message": "성공적으로 유저를 조회했습니다.",
                                      "result": {
                                        "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
                                        "tokenType": "Bearer",
                                        "memberId": 1,
                                        "email": "jio@example.com"
                                      }
                                    }
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "이메일 또는 비밀번호 불일치")
    })
    com.example.umc10th.global.apiPayload.ApiResponse<MemberResDTO.LoginResultDto> login(
            @RequestBody @Valid MemberReqDTO.LoginDto dto);
}
