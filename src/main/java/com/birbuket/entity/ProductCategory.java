package com.birbuket.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_categories")

// Homepage Category
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,  unique = true,length = 50)
    String title;

    @Column(nullable = false,  unique = true, length = 200)
    String subtitle;

    @Column(nullable = false)
    String imageUrl;
}
