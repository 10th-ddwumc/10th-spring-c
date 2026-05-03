package com.example.projectsetting.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manager_num")
    private Long managerNum;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

}
