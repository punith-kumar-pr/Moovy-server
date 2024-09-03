package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchedMoviesRequestDto {
    private int userId;
    private int movieId;
}
