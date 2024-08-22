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
        List<Movie> movies = movieService.getAllMovies();
        return getResponseEntity(movies);
    }
    // Title can have spaces
    @GetMapping("/{title}")
    private List<Movie> getMovieByTitle(@PathVariable("title") String title){
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByGenre(@RequestParam String genre) {
        List<Movie> movies = movieService.findMoviesByGenreName(genre);
        return getResponseEntity(movies);
    }

    private ResponseEntity<List<MovieResponseDto>> getResponseEntity(List<Movie> movies){
        List<MovieResponseDto> responseDtos = movies.stream().map(movie -> {
            MovieResponseDto dto = new MovieResponseDto();
            dto.setAdult(movie.getAdult());
            dto.setRuntime(movie.getRuntime());
            dto.setTagline(movie.getTagline());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setId(movie.getMovieId());
            dto.setTitle(movie.getTitle());
            dto.setImageUrl(movie.getImageUrl());
            dto.setTrailerUrl(movie.getTrailerUrl());
            dto.setVoteAverage(movie.getVoteAverage());
            dto.setVoteCount(movie.getVoteCount());
            dto.setSummary(movie.getSummary());

            dto.setGenres(movie.getMovieGenres().stream()
                    .map(mg -> {
                        MovieResponseDto.GenreDto genreDto = new MovieResponseDto.GenreDto();
                        genreDto.setId(mg.getGenre().getGenreId());
                        genreDto.setGenreName(mg.getGenre().getGenreName());
                        return genreDto;
                    })
                    .collect(Collectors.toList()));

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }
}
