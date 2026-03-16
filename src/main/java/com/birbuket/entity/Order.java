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
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Total price boş ola bilməz")
    @PositiveOrZero(message = "Total price mənfi ola bilməz")
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    BigDecimal totalPrice;

    @PositiveOrZero(message = "Discount price mənfi ola bilməz")
    @Column(name = "discount_price", precision = 10, scale = 2)
    BigDecimal discountPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promocode_id")
    PromoCode promoCode;

    @NotNull(message = "Quantity boş ola bilməz")
    @Min(value = 1, message = "Quantity ən azı 1 olmalıdır")
    @Column(nullable = false)
    Integer quantity;

    @PositiveOrZero(message = "Delivery fee mənfi ola bilməz")
    @Column(name = "delivery_fee", precision = 10, scale = 2)
    BigDecimal deliveryFee;
}