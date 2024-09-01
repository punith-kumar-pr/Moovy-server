package com.moovy.service.impls;

import com.moovy.entity.Movie;
import com.moovy.repository.MovieRepository;
import com.moovy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() { return movieRepository.findAll();}
    public List<Movie> getTopRatedMovies() { return movieRepository.findTopRatedMovies();}

    // Get movie by Title
    @Override
    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    public List<Movie> findMoviesByGenreName(String genreName) {
        return movieRepository.findMoviesByGenreName(genreName);
    }

    public List<Movie> searchMovies(String query,String genreName) {
        return movieRepository.searchMovies(query,genreName);
    }

    public List<Movie> getMoviesByGenres(List<String> genres) {
        return movieRepository.findMoviesByGenres(genres);
    }

}
