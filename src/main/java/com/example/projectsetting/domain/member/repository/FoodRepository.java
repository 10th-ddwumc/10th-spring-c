package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.Food;
import com.example.projectsetting.domain.member.enums.FoodName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByNameIn(List<FoodName> foodNames);
}
