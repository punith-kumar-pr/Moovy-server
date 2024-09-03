package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchedMoviesResponseDto {
    private Long id;
    private int userId;
    private int movieId;
}
