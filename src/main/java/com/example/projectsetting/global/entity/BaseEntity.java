package com.example.projectsetting.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    @Column(name = "deleted")
    private LocalDateTime deleted;
}
