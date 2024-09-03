package com.moovy.repository;

import com.moovy.entity.Movie;
import com.moovy.entity.User;
import com.moovy.entity.WatchedMovies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchedMoviesRepository extends JpaRepository<WatchedMovies, Long> {

    boolean existsByUserAndMovie(User user, Movie movie);

    List<WatchedMovies> findByUser_UserId(int userId);

    // Method to delete a watched movies entry by user ID and movie ID
    void deleteByUser_UserIdAndMovie_MovieId(int userId, int movieId);
}
