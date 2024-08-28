package com.moovy.service;

import com.moovy.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getTopRatedMovies();
    List<Movie> getMovieByTitle(String title);
    List<Movie> findMoviesByGenreName(String genreName);
    List<Movie> getMoviesByGenres(List<String> genres);
    List<Movie> searchMovies(String query);
}
