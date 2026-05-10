package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query(
            "select r from Review r " +
            "left join fetch r.reply " +
                    "where r.member.id = :memberId " +
                    "and r.id < :idIsLessThan " +
                    "order by r.id desc"
    )
    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("idIsLessThan") Long idIsLessThan,
            Pageable pageable);

    @Query(
            "select r from Review r " +
                    "left join fetch r.reply " +
                    "where r.member.id = :memberId " +
                    "order by r.id desc"
    )
    Slice<Review> findReviewsByMember_IdOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable);
}
