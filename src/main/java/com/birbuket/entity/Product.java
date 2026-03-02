package com.birbuket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")


// Umumi mehsullarin siyahisi
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String productName;
    String description;
    String composition;
    BigDecimal price;
    BigDecimal discountPercentage;
    boolean active;

    @Column(nullable = false)
    String color;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    boolean isSingle = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<ProductSize> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    ProductCategory productCategory;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<ProductReview> productReviews = new ArrayList<>();
}
