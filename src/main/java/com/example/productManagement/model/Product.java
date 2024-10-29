package com.example.productManagement.model;

import com.example.productManagement.constant.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends IdDateAudit {

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    @Positive
    private Double price;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

}

