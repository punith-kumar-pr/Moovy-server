package com.moovy.controller;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.entity.User;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    private User createUser (@RequestBody User user) {
         return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private User updateUser (@RequestBody User user, @PathVariable int id) {
        return userService.updateUser(user, id);
    }

    @PutMapping("change-password/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private User changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable int id) {
        return userService.changePassword(changePasswordDto, id);
    }

    @PutMapping("change-contact/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private User changeContact(
            @RequestBody ChangeContactDto changeContactDto,
            @PathVariable int id) {
        return userService.changeContact(changeContactDto, id);
    }
}
