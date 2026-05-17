package com.example.projectsetting.global.security.service;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.exception.MemberException;
import com.example.projectsetting.domain.member.exception.code.MemberErrorCode;
import com.example.projectsetting.domain.member.repository.MemberRepository;
import com.example.projectsetting.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return new AuthMember(member);

    }
}
