package com.moovy.service.impls;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.entity.User;
import com.moovy.repository.UserRepository;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, int userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();

            // Update only non-null fields from the incoming user object
            if (user.getUsername() != null) updatedUser.setUsername(user.getUsername());
            if (user.getFirstName() != null) updatedUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) updatedUser.setLastName(user.getLastName());
            if (user.getDob() != null) updatedUser.setDob(user.getDob());
            if (user.getGender() != null) updatedUser.setGender(user.getGender());
            updatedUser.setUpdatedAt(LocalDate.now());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @Override
    public User changePassword(ChangePasswordDto changePasswordDto, int userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // check for current password matches or not.
            // doing encryption thing later
            if (changePasswordDto.getCurrentPassword() != null) {
                if (!changePasswordDto.getCurrentPassword().equals(user.getPassword())) {
                    throw new RuntimeException("Incorrect Current Password");
                }
            }
            user.setPassword(changePasswordDto.getNewPassword()); // Hash the new password
            user.setUpdatedAt(LocalDate.now());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @Override
    public User changeContact(ChangeContactDto changeContactDto, int userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // check for password matches or not.
            // doing encryption thing later
            if (changeContactDto.getPassword() != null) {
                if (!changeContactDto.getPassword().equals(user.getPassword())) {
                    throw new RuntimeException("Password Incorrect");
                }
            }
            if (changeContactDto.getEmail() != null) user.setEmail(changeContactDto.getEmail());
            if(changeContactDto.getMobile() != null) user.setMobile(changeContactDto.getMobile());
            user.setUpdatedAt(LocalDate.now());
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}
