package com.example.projectsetting.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.projectsetting.domain.member.enums.FoodName;
import com.example.projectsetting.domain.member.enums.Gender;
import com.example.projectsetting.domain.member.enums.TermName;
import lombok.Builder;

public class MemberReqDTO {

   public record Signup(
           String name,
           String phoneNum,
           Gender gender,
           LocalDate birth,
           String address,
           String email,
           String password,
           List<TermName> agreedTerms,
           List<FoodName> favoriteFoods
   ){}

   public record Login(
           String email,
           String password
   ){}
}