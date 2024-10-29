package com.example.productManagement.model;

import com.example.productManagement.util.CommonUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class UserDateAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    private String updatedBy;

    @PrePersist
    public void prePersist() {
        this.setCreatedTime(LocalDateTime.now());
        this.setCreatedBy(CommonUtil.currentUser());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedTime(LocalDateTime.now());
        this.setUpdatedBy(CommonUtil.currentUser());
    }
}
