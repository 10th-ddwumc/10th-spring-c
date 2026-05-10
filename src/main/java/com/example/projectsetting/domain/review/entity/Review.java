package com.example.projectsetting.domain.review.entity;

import com.example.projectsetting.domain.member.entity.Member;
import com.example.projectsetting.domain.mission.entity.Store;
import com.example.projectsetting.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //리뷰 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //리뷰 대상 가게
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    //별점
    @Column(name = "rating", nullable = false)
    private Integer rating;

    //리뷰 내용
    @Column(name = "content", nullable = false)
    private String content;
}
