package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewDto {
    private Integer id; // movie_crew ID
    private PersonDto person;
    private String job;
    private String department;
}
