package com.example.demo.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "is_active")
    boolean activeState = true;

    @CreatedBy
    @Hidden
    @Column(name = "created_by")
    String createdBy;

    @CreatedDate
    @Hidden
    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Hidden
    @Column(name = "update_by")
    String updatedBy;

    @LastModifiedDate
    @Hidden
    @Column(name = "update_date")
    LocalDateTime updatedDate;
}
