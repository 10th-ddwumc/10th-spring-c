package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
