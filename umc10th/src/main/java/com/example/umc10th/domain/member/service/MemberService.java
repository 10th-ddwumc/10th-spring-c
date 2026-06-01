package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.config.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfoResDTO(member);
    }

    // 회원가입 (BCrypt 솔트 처리)
    @Transactional
    public MemberResDTO.SignUpResultDto signUp(MemberReqDTO.SignUpDto dto) {
        // 이메일 중복 검사
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 비밀번호 BCrypt 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());

        // DTO → 엔티티 변환 후 저장
        Member member = MemberConverter.toMember(dto, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResultDto(savedMember);
    }

    // 로그인 → JWT 발급
    public MemberResDTO.LoginResultDto login(MemberReqDTO.LoginDto dto) {
        // 이메일/비밀번호 검증 (실패 시 Spring Security가 예외 던짐)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        Member member = (Member) authentication.getPrincipal();
        String token = jwtUtil.generateToken(member);

        return MemberResDTO.LoginResultDto.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .memberId(member.getId())
                .email(member.getEmail())
                .build();
    }
}
