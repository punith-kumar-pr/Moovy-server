package com.moovy.controller;

import com.moovy.dto.*;
import com.moovy.entity.WatchedMovies;
import com.moovy.service.WatchedMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchedmovies")
public class WatchedMoviesController {

    @Autowired
    private WatchedMoviesService watchedMoviesService;

    @PostMapping
    public ResponseEntity<WatchedMoviesResponseDto> addToWatchedMovies(@RequestBody WatchedMoviesRequestDto watchedMoviesRequestDto) {
        WatchedMovies watchedMovies = watchedMoviesService.addToWatchedMovies(watchedMoviesRequestDto);

        WatchedMoviesResponseDto responseDto = new WatchedMoviesResponseDto();
        responseDto.setId(watchedMovies.getId());
        responseDto.setUserId(watchedMovies.getUser().getUserId());
        responseDto.setMovieId(watchedMovies.getMovie().getMovieId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}/movies")
    public ResponseEntity<List<MovieResponseDto>> getMoviesInWatchedMovies(@PathVariable int userId) {
        List<MovieResponseDto> movies = watchedMoviesService.getMoviesInWatchedMoviesByUserId(userId);
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("user/{userId}/remove-movie/{movieId}")
    public ResponseEntity<String> removeMovieFromWatchedMovies(
            @PathVariable int userId,
            @PathVariable int movieId) {

        watchedMoviesService.removeMovieFromWatchedMovies(userId, movieId);
        return ResponseEntity.ok("Movie removed from watch list successfully.");
    }
}
