package com.moovy.repository;

import com.moovy.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> getMovieByTitle(String title);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.movieGenres mg JOIN mg.genre g WHERE g.genreName = :genreName")
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);

    @Query("SELECT m FROM Movie m " +
            "LEFT JOIN FETCH m.movieGenres mg " +
            "LEFT JOIN FETCH mg.genre g " +
            "ORDER BY m.voteAverage DESC, m.voteCount DESC")
    List<Movie> findTopRatedMovies();
}
