package com.moovy.service;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.entity.User;

public interface UserService {
    User createUser(User user);
    User updateUser(User user, int userId);
    User changePassword(ChangePasswordDto changePasswordDto, int id);
    User changeContact(ChangeContactDto changeContactDto, int id);
}
