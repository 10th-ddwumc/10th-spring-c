package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            @NotNull(message = "id값은 필수입니다.")
            Long id
    ) {}

    public record signUp(
            @NotBlank(message = "이름은 필수입니다.")
            @Size(max = 20, message = "이름은 20자 이하로 입력해주세요.")
            String name,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 오늘 이전 날짜여야 합니다.")
            LocalDate birth,

            @NotBlank(message = "주소는 필수입니다.")
            Address address,

            String detailAddress,

            @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해주세요.")
            List<Long> foods,

            List<TermsAgreed> terms,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8~20자여야 합니다.")
            @Pattern(
                    regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=-]{8,20}$",
                    message = "비밀번호는 영문과 숫자를 포함해야 합니다."
            )
            String password
    ) {}


    public record TermsAgreed(
            Long termId,
            Boolean agreed
    ) {
    }
}
