package com.moovy.service;

import com.moovy.dto.LoginRequest;
import com.moovy.dto.RegisterRequest;
import com.moovy.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
