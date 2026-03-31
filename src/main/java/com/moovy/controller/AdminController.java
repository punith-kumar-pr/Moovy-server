package com.moovy.controller;

import com.moovy.dto.UserProfileDto;
import com.moovy.entity.ERole;
import com.moovy.entity.Role;
import com.moovy.entity.User;
import com.moovy.exception.ResourceNotFoundException;
import com.moovy.repository.RoleRepository;
import com.moovy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        List<UserProfileDto> users = userRepository.findAll().stream()
                .map(this::mapToProfileDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{userId}/roles")
    public ResponseEntity<String> assignRoles(@PathVariable int userId, @RequestBody Set<String> strRoles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Set<Role> roles = strRoles.stream()
                .map(role -> {
                    switch (role) {
                        case "admin":
                            return roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new ResourceNotFoundException("Role not found: ADMIN"));
                        case "mod":
                            return roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new ResourceNotFoundException("Role not found: MODERATOR"));
                        default:
                            return roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new ResourceNotFoundException("Role not found: USER"));
                    }
                })
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("Roles updated successfully!");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        user.setIsActive(false);
        userRepository.save(user);

        return ResponseEntity.ok("User deactivated successfully!");
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
