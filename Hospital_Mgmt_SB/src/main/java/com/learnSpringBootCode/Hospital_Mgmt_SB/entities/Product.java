package com.learnSpringBootCode.Hospital_Mgmt_SB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.ru.INN;
import org.springframework.data.annotation.CreatedDate;

import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="product_mst_learn_jpa")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
