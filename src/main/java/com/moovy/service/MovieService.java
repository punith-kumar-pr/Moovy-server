package com.moovy.service;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.Movie;

import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getAllMovies();
    List<MovieResponseDto> getTopRatedMovies();
    List<MovieResponseDto> getMovieByTitle(String title);
    List<MovieResponseDto> findMoviesByGenreName(String genreName);
    List<MovieResponseDto> getMoviesByGenres(List<String> genres);
    List<MovieResponseDto> searchMovies(String query,String genreName);
}
