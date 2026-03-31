package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getMovies() {
        List<MovieResponseDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponseDto>> getTopRatedMovies() {
        List<MovieResponseDto> movies = movieService.getTopRatedMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<MovieResponseDto>> getMovieByTitle(@PathVariable("title") String title) {
        List<MovieResponseDto> movies = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByGenre(@RequestParam String genre) {
        List<MovieResponseDto> movies = movieService.findMoviesByGenreName(genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDto>> searchMovies(
            @RequestParam String query,
            @RequestParam(required = false) String genreName) {
        List<MovieResponseDto> movies = movieService.searchMovies(query, genreName);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/by-genres")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByGenres(@RequestParam List<String> genres) {
        List<MovieResponseDto> movies = movieService.getMoviesByGenres(genres);
        return ResponseEntity.ok(movies);
    }
}
