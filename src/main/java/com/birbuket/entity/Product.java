package com.birbuket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")

// Ümumi məhsulların siyahısı
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Product name boş ola bilməz")
    @Size(min = 2, max = 150)
    @Column(name = "product_name", nullable = false, unique = true, length = 150)
    String productName;

    @Size(max = 1000)
    @Column(length = 1000)
    String description;

    @Size(max = 500)
    @Column(length = 500)
    String composition;

    @NotNull(message = "Price boş ola bilməz")
    @Positive(message = "Price 0-dan böyük olmalıdır")
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    @PositiveOrZero(message = "Discount percentage mənfi ola bilməz")
    @Max(value = 100, message = "Discount 100%-dən böyük ola bilməz")
    @Column(name = "discount_percentage", precision = 5, scale = 2)
    BigDecimal discountPercentage;

    @Column(nullable = false)
    boolean active;

    @NotBlank(message = "Color boş ola bilməz")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    String color;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Column(name = "is_single")
    boolean isSingle = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductSize> productSizes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductReview> productReviews = new ArrayList<>();
}