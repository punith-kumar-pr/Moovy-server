package com.moovy.service;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.WatchedMovies;

import java.util.List;

public interface WatchedMoviesService {
    WatchedMovies addToWatchedMovies(String username, int movieId);
    List<MovieResponseDto> getMoviesInWatchedMovies(String username);
    void removeMovieFromWatchedMovies(String username, int movieId);
}
