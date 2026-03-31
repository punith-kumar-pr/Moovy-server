package com.moovy.service.impls;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.dto.UpdateProfileDto;
import com.moovy.dto.UserProfileDto;
import com.moovy.entity.User;
import com.moovy.exception.BadRequestException;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.UserRepository;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return mapToProfileDto(user);
    }

    @Override
    public UserProfileDto updateProfile(String username, UpdateProfileDto dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getDob() != null) user.setDob(dto.getDob());
        if (dto.getGender() != null) {
            try {
                user.setGender(User.Gender.valueOf(dto.getGender().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid gender value. Allowed values: MALE, FEMALE");
            }
        }
        user.setUpdatedAt(LocalDate.now());
        User savedUser = userRepository.save(user);
        return mapToProfileDto(savedUser);
    }

    @Override
    public void changePassword(String username, ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        // Verify current password using BCrypt comparison
        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        // Encode new password before saving
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public void changeContact(String username, ChangeContactDto changeContactDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        if (changeContactDto.getEmail() != null) {
            // Check if the new email is already taken by another user
            userRepository.findByEmail(changeContactDto.getEmail())
                    .filter(existingUser -> existingUser.getUserId() != user.getUserId())
                    .ifPresent(existingUser -> {
                        throw new BadRequestException("Email is already in use by another account");
                    });
            user.setEmail(changeContactDto.getEmail());
        }
        if (changeContactDto.getMobile() != null) {
            user.setMobile(changeContactDto.getMobile());
        }
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    private UserProfileDto mapToProfileDto(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setGender(user.getGender() != null ? user.getGender().name() : null);
        dto.setMobile(user.getMobile());
        dto.setDob(user.getDob());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setRoles(user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList()));
        return dto;
    }
}
