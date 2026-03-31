package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private int userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;
    private LocalDate dob;
    private LocalDate createdAt;
    private List<String> roles;
}
