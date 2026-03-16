package com.birbuket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promo_code")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Title boş ola bilməz")
    @Size(max = 100, message = "Title maksimum 100 simvol ola bilər")
    @Column(nullable = false, length = 100)
    String title;

    @NotBlank(message = "Promo code boş ola bilməz")
    @Pattern(regexp = "^[A-Z0-9]{4,20}$", message = "Promo code yalnız böyük hərf və rəqəmdən ibarət olmalıdır, 4–20 simvol")
    @Column(nullable = false, unique = true, length = 20)
    String promoCode;

    @NotNull(message = "Discount price boş ola bilməz")
    @Positive(message = "Discount price 0-dan böyük olmalıdır")
    @Column(name = "discount_price", nullable = false, precision = 10, scale = 2)
    BigDecimal discountPrice;

    @NotNull(message = "Start date boş ola bilməz")
    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @NotNull(message = "Expiry date boş ola bilməz")
    @Column(name = "expiry_date", nullable = false)
    LocalDate expiryDate;

    @NotNull(message = "Usage limit boş ola bilməz")
    @Min(value = 1, message = "Usage limit ən azı 1 olmalıdır")
    @Column(name = "usage_limit", nullable = false)
    Integer usageLimit;
}