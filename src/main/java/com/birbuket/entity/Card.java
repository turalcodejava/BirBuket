package com.birbuket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Kart sahibinin adı boş ola bilməz")
    @Size(min = 3, max = 100)
    @Pattern(
            regexp = "^[A-Za-zƏəĞğİıÖöŞşÜüÇç\\s]+$",
            message = "Ad yalnız hərflərdən ibarət olmalıdır"
    )
    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @NotBlank(message = "Kart nömrəsi boş ola bilməz")
    @Pattern(
            regexp = "^[0-9]{16}$",
            message = "Kart nömrəsi 16 rəqəmdən ibarət olmalıdır"
    )
    @Column(name = "card_number", nullable = false, length = 16)
    String cardNumber;

    @NotNull(message = "Bitmə tarixi boş ola bilməz")
    @Future(message = "Kartın bitmə tarixi gələcək tarix olmalıdır")
    @Column(name = "expiration_date", nullable = false)
    LocalDate expirationDate;

    @NotBlank(message = "CVV boş ola bilməz")
    @Pattern(
            regexp = "^[0-9]{3,4}$",
            message = "CVV 3 və ya 4 rəqəm olmalıdır"
    )
    @Column(nullable = false, length = 4)
    String cvv;
}