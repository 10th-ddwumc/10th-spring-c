package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public class MemberReqDTO {
    @Getter
    public static class RequestBody {
        private String stringTest;
        private Long longTest;
    }

    // 마이페이지
    @Getter
    public static class GetInfo {
        private Long id;
    }

    // 로그인
    public record LoginDto(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.") String email,
            @NotBlank(message = "비밀번호는 필수입니다.") String password
    ) {}

    // 회원가입
    public record SignUpDto(
            @NotBlank(message = "이름은 필수입니다.") String name,
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.") String email,
            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.") String password,
            @NotNull(message = "생년월일은 필수입니다.") LocalDate birth,
            Gender gender,
            @NotNull(message = "주소는 필수입니다.") Address address,
            @NotBlank(message = "상세주소는 필수입니다.") String detailAddress,
            @NotBlank(message = "전화번호는 필수입니다.") String phoneNumber
    ) {}
}
