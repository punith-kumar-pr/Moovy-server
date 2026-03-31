package com.moovy.controller;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.dto.UpdateProfileDto;
import com.moovy.dto.UserProfileDto;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/me")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(Principal principal) {
        UserProfileDto profile = userService.getProfile(principal.getName());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(
            @RequestBody UpdateProfileDto updateProfileDto,
            Principal principal) {
        UserProfileDto profile = userService.updateProfile(principal.getName(), updateProfileDto);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            Principal principal) {
        userService.changePassword(principal.getName(), changePasswordDto);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/change-contact")
    public ResponseEntity<String> changeContact(
            @RequestBody ChangeContactDto changeContactDto,
            Principal principal) {
        userService.changeContact(principal.getName(), changeContactDto);
        return ResponseEntity.ok("Contact information updated successfully");
    }
}
