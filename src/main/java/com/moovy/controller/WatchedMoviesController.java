package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchedMoviesRequestDto;
import com.moovy.dto.WatchedMoviesResponseDto;
import com.moovy.entity.WatchedMovies;
import com.moovy.service.WatchedMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/watched-movies")
public class WatchedMoviesController {

    @Autowired
    private WatchedMoviesService watchedMoviesService;

    @PostMapping
    public ResponseEntity<WatchedMoviesResponseDto> addToWatchedMovies(
            @RequestBody WatchedMoviesRequestDto watchedMoviesRequestDto,
            Principal principal) {
        WatchedMovies watchedMovies = watchedMoviesService.addToWatchedMovies(
                principal.getName(), watchedMoviesRequestDto.getMovieId());

        WatchedMoviesResponseDto responseDto = new WatchedMoviesResponseDto();
        responseDto.setId(watchedMovies.getId());
        responseDto.setUserId(watchedMovies.getUser().getUserId());
        responseDto.setMovieId(watchedMovies.getMovie().getMovieId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getWatchedMovies(Principal principal) {
        List<MovieResponseDto> movies = watchedMoviesService.getMoviesInWatchedMovies(principal.getName());
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> removeFromWatchedMovies(
            @PathVariable int movieId,
            Principal principal) {
        watchedMoviesService.removeMovieFromWatchedMovies(principal.getName(), movieId);
        return ResponseEntity.ok("Movie removed from watched list successfully.");
    }
}
