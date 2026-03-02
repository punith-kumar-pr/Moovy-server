package com.moovy.service;

import com.moovy.dto.MovieResponseDto;
import com.moovy.dto.WatchListRequestDto;
import com.moovy.entity.WatchList;

import java.util.List;

public interface WatchListService {
    WatchList addToWatchList(WatchListRequestDto watchListRequestDto);
    List<MovieResponseDto> getMoviesInWatchListByUserId(int userId);
    void removeMovieFromWatchList(int userId, int movieId);
}