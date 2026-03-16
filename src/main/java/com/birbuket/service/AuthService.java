package com.birbuket.service;

import com.birbuket.dto.UserLoginRequest;
import com.birbuket.dto.UserLoginResponse;
import com.birbuket.dto.UserRegisterRequest;
import com.birbuket.dto.UserRegisterResponse;

public interface AuthService {

    UserRegisterResponse register(UserRegisterRequest request);
    UserLoginResponse login(UserLoginRequest request);
}
