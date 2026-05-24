package com.example.projectsetting.domain.member.repository;

import com.example.projectsetting.domain.member.entity.Term;
import com.example.projectsetting.domain.member.enums.TermName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term,Long> {

    List<Term> findAllByNameIn(List<TermName> termNames);
}
