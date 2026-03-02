package com.moovy.service;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchedMoviesRequestDto;
import com.moovy.entity.WatchedMovies;

import java.util.List;

public interface WatchedMoviesService {
    WatchedMovies addToWatchedMovies(WatchedMoviesRequestDto watchedMoviesRequestDto);
    List<MovieResponseDto> getMoviesInWatchedMoviesByUserId(int userId);
    void removeMovieFromWatchedMovies(int userId, int movieId);
}
