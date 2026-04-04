package com.moovy.service;

import com.moovy.dto.RatingRequestDto;
import com.moovy.dto.RatingResponseDto;

import java.util.List;

public interface RatingService {
    RatingResponseDto rateMovie(String username, RatingRequestDto ratingRequestDto);
    RatingResponseDto updateRating(String username, RatingRequestDto ratingRequestDto);
    List<RatingResponseDto> getMyRatings(String username);
    List<RatingResponseDto> getRatingsForMovie(int movieId);
    void deleteRating(String username, int movieId);
}
