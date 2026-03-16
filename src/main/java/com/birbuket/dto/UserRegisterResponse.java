package com.birbuket.dto;

import com.birbuket.enums.Gender;
import com.birbuket.enums.Role;
import com.birbuket.enums.UserStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String username;

    private Gender gender;

    private LocalDate birthDate;

    private Role role;

    private UserStatus status;
}