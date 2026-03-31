package com.moovy.service;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.WatchList;

import java.util.List;

public interface WatchListService {
    WatchList addToWatchList(String username, int movieId);
    List<MovieResponseDto> getMoviesInWatchList(String username);
    void removeMovieFromWatchList(String username, int movieId);
}