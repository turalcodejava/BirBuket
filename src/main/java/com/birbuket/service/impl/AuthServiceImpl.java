package com.birbuket.service.impl;


import com.birbuket.dto.UserLoginRequest;
import com.birbuket.dto.UserLoginResponse;
import com.birbuket.dto.UserRegisterRequest;
import com.birbuket.dto.UserRegisterResponse;
import com.birbuket.exception.PasswordMismatchException;
import com.birbuket.exception.UserAlreadyExistsException;
import com.birbuket.exception.UserNotFoundException;
import com.birbuket.mapper.UserMapper;
import com.birbuket.repository.UserRepository;
import com.birbuket.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords don't match");
        }
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists with that name");
        }
        var user = userMapper.toUserEntity(request);
        userRepository.save(user);
        return userMapper.toUserRegisterResponse(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User not found with username: " + request.getUsername()));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Password mismatch");
        }
        return new UserLoginResponse(user.getUsername());
    }
}
