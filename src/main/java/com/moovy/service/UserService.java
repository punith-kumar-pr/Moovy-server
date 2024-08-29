package com.moovy.service;

import com.moovy.entity.User;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, int userId);
}
