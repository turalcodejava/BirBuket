package com.birbuket.entity;


import com.birbuket.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_item")

// Product for Render
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false, unique = true)
    String imageUrl;

    @Enumerated(EnumType.STRING)
    ProductType  productType;
}
