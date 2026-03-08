package com.moovy.controller;

import com.moovy.entity.ERole;
import com.moovy.entity.Role;
import com.moovy.entity.User;
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
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/users/{userId}/roles")
    public ResponseEntity<?> assignRoles(@PathVariable int userId, @RequestBody Set<String> strRoles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        Set<Role> roles = strRoles.stream()
                .map(role -> {
                    switch (role) {
                        case "admin":
                            return roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        case "mod":
                            return roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        default:
                            return roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    }
                })
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("Roles updated successfully!");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deactivateUser(@PathVariable int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        user.setIsActive(false);
        userRepository.save(user);

        return ResponseEntity.ok("User deactivated successfully!");
    }
}
