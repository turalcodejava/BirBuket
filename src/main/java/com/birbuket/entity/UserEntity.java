package com.birbuket.entity;


import com.birbuket.enums.Gender;
import com.birbuket.enums.Role;
import com.birbuket.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private  String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  UserStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Addresses> addresses = new ArrayList<>();

}
