package com.birbuket.entity;


import com.birbuket.enums.Gender;
import com.birbuket.enums.Role;
import com.birbuket.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false, unique = true)
    String phoneNumber;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;

    LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserStatus status;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Addresses> addresses = new ArrayList<>();

}
