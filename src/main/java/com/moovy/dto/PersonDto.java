package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Integer personId;
    private String name;
    private LocalDate birthdate;
    private String nationality;
    private String profileImageUrl;
    private String biography;
}
