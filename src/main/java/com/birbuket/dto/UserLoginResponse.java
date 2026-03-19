package com.birbuket.dto;


import com.birbuket.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponse {

    private String username;
    private String accessToken;
    private String refreshToken;
    private Role role;
}
