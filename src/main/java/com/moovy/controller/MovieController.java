package com.moovy.controller;

import com.moovy.dtos.MovieGenreDTO;
import com.moovy.entity.Movie;
import com.moovy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    private List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    // Title can have spaces
    @GetMapping("/{title}")
    private List<Movie> getMovieByTitle(@PathVariable("title") String title){
        return movieService.getMovieByTitle(title);
    }

    // getting Movies By genre
    @GetMapping("/by-genre")
    public List<MovieGenreDTO> getMoviesByGenre(@RequestParam String genre) {
        return movieService.getMoviesByGenre(genre);
    }

}
