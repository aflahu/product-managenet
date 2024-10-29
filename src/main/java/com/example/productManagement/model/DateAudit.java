package com.example.productManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class DateAudit implements Serializable {

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name= "updated_time")
    private LocalDateTime updatedTime;

    @PrePersist
    public void prePersist(){
        this.setCreatedTime(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        this.setUpdatedTime(LocalDateTime.now());
    }

}
