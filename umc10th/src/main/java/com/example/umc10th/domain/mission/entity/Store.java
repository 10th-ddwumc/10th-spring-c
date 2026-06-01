package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="rating", nullable = false)
    private Float rating;

    @Column(name="address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id")
    private Location location;

    //연관관계
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> MissionList=new ArrayList<>();


}
