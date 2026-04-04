package com.moovy.controller;

import com.moovy.dto.ReviewRequestDto;
import com.moovy.dto.ReviewResponseDto;
import com.moovy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(
            @RequestBody ReviewRequestDto reviewRequestDto,
            Principal principal) {
        ReviewResponseDto responseDto = reviewService.addReview(principal.getName(), reviewRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<ReviewResponseDto> updateReview(
            @RequestBody ReviewRequestDto reviewRequestDto,
            Principal principal) {
        ReviewResponseDto responseDto = reviewService.updateReview(principal.getName(), reviewRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getMyReviews(Principal principal) {
        List<ReviewResponseDto> reviews = reviewService.getMyReviews(principal.getName());
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable int movieId,
            Principal principal) {
        reviewService.deleteReview(principal.getName(), movieId);
        return ResponseEntity.ok("Review deleted successfully.");
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsForMovie(@PathVariable int movieId) {
        List<ReviewResponseDto> reviews = reviewService.getReviewsForMovie(movieId);
        return ResponseEntity.ok(reviews);
    }
}
