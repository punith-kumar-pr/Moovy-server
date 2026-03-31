package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDto {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;
}
