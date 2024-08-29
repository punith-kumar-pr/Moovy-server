package com.moovy.controller;

import com.moovy.dto.UserRegistrationDto;
import com.moovy.entity.User;
import com.moovy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    private User createUser (@RequestBody UserRegistrationDto userDto) {
         return userService.createUser(userDto);
    }
}
