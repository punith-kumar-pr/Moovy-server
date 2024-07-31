package com.moovy.service;

import com.moovy.MovieGenreDTO;
import com.moovy.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getMovieByTitle(String title);
    List<MovieGenreDTO> getMoviesByGenre(String genreName);
}
