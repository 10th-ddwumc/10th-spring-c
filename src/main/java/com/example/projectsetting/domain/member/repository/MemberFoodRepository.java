package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
