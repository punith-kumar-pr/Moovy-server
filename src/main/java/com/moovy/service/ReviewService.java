package com.moovy.service;

import com.moovy.dto.ReviewRequestDto;
import com.moovy.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto addReview(String username, ReviewRequestDto reviewRequestDto);
    ReviewResponseDto updateReview(String username, ReviewRequestDto reviewRequestDto);
    List<ReviewResponseDto> getMyReviews(String username);
    List<ReviewResponseDto> getReviewsForMovie(int movieId);
    void deleteReview(String username, int movieId);
}
