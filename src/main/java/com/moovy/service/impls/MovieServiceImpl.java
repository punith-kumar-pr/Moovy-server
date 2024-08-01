package com.moovy.service.impls;

import com.moovy.dtos.MovieGenreDTO;
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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get movie by Title
    @Override
    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    @Override
    public List<MovieGenreDTO> getMoviesByGenre(String genreName) {
        return movieRepository.findMoviesByGenreName(genreName);
    }

}
