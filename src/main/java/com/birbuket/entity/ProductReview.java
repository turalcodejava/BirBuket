package com.birbuket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 500, message = "Review maksimum 500 simvol ola bilər")
    @Column(length = 500)
    String review;

    @NotNull(message = "Rating boş ola bilməz")
    @Min(value = 1, message = "Rating ən azı 1 olmalıdır")
    @Max(value = 5, message = "Rating ən çox 5 ola bilər")
    @Column(nullable = false)
    Integer rate;

    @NotNull(message = "Product boş ola bilməz")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @NotNull(message = "User boş ola bilməz")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
}