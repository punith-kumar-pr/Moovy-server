package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastDto {
    private Integer id; // movie_cast ID
    private PersonDto person;
    private String characterName;
    private Integer order;
}
