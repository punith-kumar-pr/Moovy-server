package com.moovy.service;

import com.moovy.dto.UserRegistrationDto;
import com.moovy.entity.User;

public interface UserService {
    User createUser(UserRegistrationDto userDto);
}
