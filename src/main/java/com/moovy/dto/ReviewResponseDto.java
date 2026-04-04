package com.moovy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private int reviewId;
    private int movieId;
    private String movieTitle;
    private String username;
    private String reviewText;
    private Date reviewDate;
}
