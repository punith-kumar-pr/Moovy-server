package com.moovy.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationDto {
    private String username;
    private String password;  // Plain text password for registration
    private String email;
}
