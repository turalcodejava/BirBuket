package com.birbuket.controller;

import com.birbuket.dto.*;
import com.birbuket.service.AuthService;
import com.birbuket.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication API")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Operation(summary = "User registration")
    public ResponseEntity<@NonNull ApiResponse<UserRegisterResponse>> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok().body(ApiResponse.success(authService.register(request)));
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<@NonNull ApiResponse<UserLoginResponse>> loginUser(@Valid @RequestBody UserLoginRequest request) {
        var response = authService.login(request);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<@NonNull ApiResponse<UserLoginResponse>> refresh(
            @RequestParam String refreshToken
    ) {
        var response = authService.refresh(refreshToken);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<@NonNull ApiResponse<String>> logout(@RequestParam String refreshToken) {
        refreshTokenService.revokeRefreshToken(refreshToken);
        return ResponseEntity.ok(ApiResponse.success("Successfully logged out"));
    }

}
