package com.birbuket.service.impl;


import com.birbuket.dto.UserLoginRequest;
import com.birbuket.dto.UserLoginResponse;
import com.birbuket.dto.UserRegisterRequest;
import com.birbuket.dto.UserRegisterResponse;
import com.birbuket.enums.Role;
import com.birbuket.enums.UserStatus;
import com.birbuket.exception.PasswordMismatchException;
import com.birbuket.exception.UserAlreadyExistsException;
import com.birbuket.exception.UserNotFoundException;
import com.birbuket.mapper.UserMapper;
import com.birbuket.repository.UserRepository;
import com.birbuket.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("Attempting to register user: {}", request.getUsername());

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.warn("Registration failed: password and confirmPassword do not match");
            throw new PasswordMismatchException("Passwords don't match");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("User already exists with username: {}", request.getUsername());
            throw new UserAlreadyExistsException("Username already exists with that name");
        }
        var user = userMapper.toUserEntity(request);
        user.setRole(Role.USER);
        user.setStatus(UserStatus.PENDING);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        log.info("User registered successfully: {}", user.getUsername());

        return userMapper.toUserRegisterResponse(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        log.info("Attempting login for user: {}", request.getUsername());

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.warn("User not found: {}", request.getUsername());
                    return new UserNotFoundException("User not found with username: " + request.getUsername());
                });
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Password mismatch for user: {}", request.getUsername());
            throw new PasswordMismatchException("Password mismatch");
        }
        log.info("User logged in successfully: {}", user.getUsername());
        return new UserLoginResponse(user.getUsername());
    }
}
