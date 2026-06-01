package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서 기반 조회
    @Query(value = "SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.reply rep " +
            "WHERE r.member = :member " +
            "AND (:cursor IS NULL OR r.id < :cursor) " +
            "ORDER BY r.id DESC",
            countQuery = "SELECT COUNT(r) FROM Review r WHERE r.member = :member")
    Page<Review> findAllByMemberOrderById(
            @Param("member") Member member,
            @Param("cursor") Long lastId,
            Pageable pageable);

    // 별점 순 커서 기반 조회 (별점 DESC, id DESC)
    @Query(value = "SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.reply rep " +
            "WHERE r.member = :member " +
            "AND (:lastStarRating IS NULL " +
            "     OR r.starRating < :lastStarRating " +
            "     OR (r.starRating = :lastStarRating AND r.id < :lastId)) " +
            "ORDER BY r.starRating DESC, r.id DESC",
            countQuery = "SELECT COUNT(r) FROM Review r WHERE r.member = :member")
    Page<Review> findAllByMemberOrderByStarRating(
            @Param("member") Member member,
            @Param("lastStarRating") Integer lastStarRating,
            @Param("lastId") Long lastId,
            Pageable pageable);
}
