package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialId(SocialType providerId, String socialId);
}
