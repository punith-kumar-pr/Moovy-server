package com.moovy.controller;

import com.moovy.dto.RatingRequestDto;
import com.moovy.dto.RatingResponseDto;
import com.moovy.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponseDto> rateMovie(
            @RequestBody RatingRequestDto ratingRequestDto,
            Principal principal) {
        RatingResponseDto responseDto = ratingService.rateMovie(principal.getName(), ratingRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<RatingResponseDto> updateRating(
            @RequestBody RatingRequestDto ratingRequestDto,
            Principal principal) {
        RatingResponseDto responseDto = ratingService.updateRating(principal.getName(), ratingRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<RatingResponseDto>> getMyRatings(Principal principal) {
        List<RatingResponseDto> ratings = ratingService.getMyRatings(principal.getName());
        return ResponseEntity.ok(ratings);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteRating(
            @PathVariable int movieId,
            Principal principal) {
        ratingService.deleteRating(principal.getName(), movieId);
        return ResponseEntity.ok("Rating deleted successfully.");
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingResponseDto>> getRatingsForMovie(@PathVariable int movieId) {
        List<RatingResponseDto> ratings = ratingService.getRatingsForMovie(movieId);
        return ResponseEntity.ok(ratings);
    }
}
