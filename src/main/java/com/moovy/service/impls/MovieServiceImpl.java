package com.moovy.service.impls;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.Movie;
import com.moovy.repository.MovieRepository;
import com.moovy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieResponseDto> getAllMovies() {
        List<Movie> movies =  movieRepository.findAll();
        return getMoviesInStructure(movies);
    }

    public List<MovieResponseDto> getTopRatedMovies() {
        List<Movie> movies = movieRepository.findTopRatedMovies();
        return getMoviesInStructure(movies);
    }

    // Get movie by Title
    @Override
    public List<MovieResponseDto> getMovieByTitle(String title) {
        List<Movie> movies = movieRepository.getMovieByTitle(title);
        return getMoviesInStructure(movies);
    }

    public List<MovieResponseDto> findMoviesByGenreName(String genreName) {
        List<Movie> movies = movieRepository.findMoviesByGenreName(genreName);
        return getMoviesInStructure(movies);
    }

    public List<MovieResponseDto> searchMovies(String query,String genreName) {
        List<Movie> movies = movieRepository.searchMovies(query,genreName);
        return getMoviesInStructure(movies);
    }

    public List<MovieResponseDto> getMoviesByGenres(List<String> genres) {
        List<Movie> movies = movieRepository.findMoviesByGenres(genres);
        return getMoviesInStructure(movies);

    }

    private List<MovieResponseDto> getMoviesInStructure(List<Movie> movies) {
        return movies.stream().map(movie -> {
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
    }

}
