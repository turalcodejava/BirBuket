package com.birbuket.entity;


import com.birbuket.enums.OrderTime;
import com.birbuket.enums.PlantsOfNumber;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

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


    @Column(nullable = false)
    String fullAddressLineSnapshot;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PlantsOfNumber plantsOfNumber;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDate doctorVisitTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderTime orderTime;

    String noteForDoctor;

    BigDecimal totalPrice;

    @Column(nullable = false, unique = true)
    String confirmationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    Addresses address;
}
