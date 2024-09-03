package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.Movie;
import com.moovy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    private ResponseEntity<List<MovieResponseDto>> getMovies() {
        List<MovieResponseDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-rated")
    private ResponseEntity<List<MovieResponseDto>> getTopRatedMovies(){
        List<MovieResponseDto> movies = movieService.getTopRatedMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{title}")
    private ResponseEntity<List<MovieResponseDto>> getMovieByTitle(@PathVariable("title") String title){
        List<MovieResponseDto> movies = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByGenre(@RequestParam String genre) {
        List<MovieResponseDto> movies = movieService.findMoviesByGenreName(genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDto>> searchMovies(@RequestParam String query,@RequestParam(required = false) String genreName) {
        List<MovieResponseDto> movies = movieService.searchMovies(query,genreName);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/by-genres")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByGenres(@RequestParam List<String> genres) {
        List<MovieResponseDto> movies = movieService.getMoviesByGenres(genres);
        return ResponseEntity.ok(movies);
    }
}
