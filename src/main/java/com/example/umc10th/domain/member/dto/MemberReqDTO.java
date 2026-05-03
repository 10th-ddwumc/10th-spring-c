package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ) {}

    public record signUp(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            List<Food> foods
    ) {}
}
