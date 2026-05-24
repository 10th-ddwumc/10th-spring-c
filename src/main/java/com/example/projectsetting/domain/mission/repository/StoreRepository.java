package com.example.projectsetting.domain.mission.repository;

import com.example.projectsetting.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
