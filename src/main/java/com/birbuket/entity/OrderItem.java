package com.birbuket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Product boş ola bilməz")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @NotNull(message = "Quantity boş ola bilməz")
    @Min(value = 1, message = "Quantity ən azı 1 olmalıdır")
    @Column(nullable = false)
    Integer quantity;

    @NotNull(message = "Total price boş ola bilməz")
    @PositiveOrZero(message = "Total price mənfi ola bilməz")
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    BigDecimal totalPrice;
}