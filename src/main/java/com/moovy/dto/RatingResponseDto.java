package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private Long ratingId;
    private int movieId;
    private String movieTitle;
    private int rating;
    private LocalDate ratingDate;
}
