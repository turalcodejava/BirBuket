package com.birbuket.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class RefreshToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Instant expiration;

    @ManyToOne
    private UserEntity user;
}
