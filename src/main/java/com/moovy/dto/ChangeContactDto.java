package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeContactDto {
    private String email;
    private String mobile;
    private String password;
}
