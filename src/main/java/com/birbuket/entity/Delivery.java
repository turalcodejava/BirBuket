package com.birbuket.entity;

import com.birbuket.enums.DeliveryHours;
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
@Table(name = "delivery")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Ad və soyad boş ola bilməz")
    @Size(min = 3, max = 100)
    @Pattern(
            regexp = "^[A-Za-zƏəĞğİıÖöŞşÜüÇç\\s]+$",
            message = "Ad yalnız hərflərdən ibarət olmalıdır"
    )
    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @NotBlank(message = "Telefon nömrəsi boş ola bilməz")
    @Pattern(
            regexp = "^\\+994[0-9]{9}$",
            message = "Telefon nömrəsi +994XXXXXXXXX formatında olmalıdır"
    )
    @Column(name = "phone_number", nullable = false, length = 13)
    String phoneNumber;

    @NotBlank(message = "Location boş ola bilməz")
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    String location;

    @Size(max = 500)
    @Column(name = "note_for_courier", length = 500)
    String noteForCourier;

    @NotNull(message = "Çatdırılma tarixi boş ola bilməz")
    @FutureOrPresent(message = "Çatdırılma tarixi keçmiş ola bilməz")
    @Column(name = "delivery_date", nullable = false)
    LocalDate deliveryDate;

    @NotNull(message = "Çatdırılma saatı seçilməlidir")
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_hours", nullable = false)
    DeliveryHours deliveryHours;
}