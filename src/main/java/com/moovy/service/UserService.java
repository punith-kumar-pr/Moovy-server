package com.moovy.service;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.dto.UpdateProfileDto;
import com.moovy.dto.UserProfileDto;

public interface UserService {
    UserProfileDto getProfile(String username);
    UserProfileDto updateProfile(String username, UpdateProfileDto updateProfileDto);
    void changePassword(String username, ChangePasswordDto changePasswordDto);
    void changeContact(String username, ChangeContactDto changeContactDto);
}
