package com.moovy.repository;

import com.moovy.dtos.MovieGenreDTO;
import com.moovy.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> getMovieByTitle(String title);
    @Query(name = "Movie.findMoviesByGenreName", nativeQuery = true)
    List<MovieGenreDTO> findMoviesByGenreName(@Param("genreName") String genreName);
}
