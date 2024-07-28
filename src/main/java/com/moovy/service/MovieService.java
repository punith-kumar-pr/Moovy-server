package com.moovy.service;

import com.moovy.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getMovieByName();
}
