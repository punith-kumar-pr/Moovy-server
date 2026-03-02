package com.moovy.controller;

import com.moovy.dto.ChangeContactDto;
import com.moovy.dto.ChangePasswordDto;
import com.moovy.entity.User;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    private User createUser (@RequestBody User user) {
         return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    private User updateUser (@RequestBody User user, @PathVariable int id) {
        return userService.updateUser(user, id);
    }

    @PutMapping("change-password/{id}")
    private User changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable int id) {
        return userService.changePassword(changePasswordDto, id);
    }

    @PutMapping("change-contact/{id}")
    private User changeContact(
            @RequestBody ChangeContactDto changeContactDto,
            @PathVariable int id) {
        return userService.changeContact(changeContactDto, id);
    }
}
