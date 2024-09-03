package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WatchListRequestDto {
    private int userId;
    private int movieId;
}

