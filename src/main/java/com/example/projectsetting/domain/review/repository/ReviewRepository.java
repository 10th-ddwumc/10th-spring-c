package com.example.projectsetting.domain.review.repository;

import com.example.projectsetting.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Slice<Review> findReviewByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, PageRequest pageRequest);

    @Query("""
        select r
        from Review r
        where r.store.id = :storeId
          and (
                r.rating < :ratingCursor
                or (
                    r.rating = :ratingCursor
                    and r.id < :idCursor
                )
              )
        order by r.rating desc, r.id desc
    """)
    Slice<Review> findReviewByRatingCursor(Long storeId, Integer ratingCursor, long idCursor, PageRequest pageRequest);

    Slice<Review> findReviewById(Long storeId, PageRequest pageRequest);

    Slice<Review> findReviewByRating(Long storeId, PageRequest pageRequest);
}

