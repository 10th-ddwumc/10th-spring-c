package com.example.projectsetting.domain.member.dto;

import java.time.LocalDate;
import com.example.projectsetting.domain.member.enums.Gender;
import lombok.Builder;

public class MemberReqDTO {

   public record Signup(
           String name,
           String phoneNum,
           Gender gender,
           LocalDate birth,
           String address
   ){}


}