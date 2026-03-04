package com.birbuket.entity;

import com.birbuket.enums.DeliveryHours;
import jakarta.persistence.*;
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

    String fullName;
    String phoneNumber;
    String location;
    String noteForCourier;
    LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    DeliveryHours deliveryHours;
}
