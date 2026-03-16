package com.birbuket.entity;

import com.birbuket.enums.OrderTime;
import com.birbuket.enums.PlantsOfNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(
        name = "doctor_orders",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_visit_time", "order_time"})
        }
)
public class DoctorOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Ünvan boş ola bilməz")
    @Size(max = 255)
    @Column(name = "full_address_line_snapshot", nullable = false, length = 255)
    String fullAddressLineSnapshot;

    @NotNull(message = "Bitki sayı seçilməlidir")
    @Enumerated(EnumType.STRING)
    @Column(name = "plants_of_number", nullable = false)
    PlantsOfNumber plantsOfNumber;

    @NotBlank(message = "Təsvir boş ola bilməz")
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    String description;

    @NotNull(message = "Həkim ziyarət tarixi boş ola bilməz")
    @FutureOrPresent(message = "Tarix keçmiş ola bilməz")
    @Column(name = "doctor_visit_time", nullable = false)
    LocalDate doctorVisitTime;

    @NotNull(message = "Order vaxtı seçilməlidir")
    @Enumerated(EnumType.STRING)
    @Column(name = "order_time", nullable = false)
    OrderTime orderTime;

    @Size(max = 500)
    @Column(name = "note_for_doctor", length = 500)
    String noteForDoctor;

    @PositiveOrZero(message = "Qiymət mənfi ola bilməz")
    @Column(name = "total_price", precision = 10, scale = 2)
    BigDecimal totalPrice;

    @NotBlank(message = "Confirmation number boş ola bilməz")
    @Pattern(
            regexp = "^[A-Z0-9]{8,20}$",
            message = "Confirmation number yalnız böyük hərf və rəqəmdən ibarət olmalıdır"
    )
    @Column(name = "confirmation_number", nullable = false, unique = true, length = 20)
    String confirmationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    Addresses address;
}