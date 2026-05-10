package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
