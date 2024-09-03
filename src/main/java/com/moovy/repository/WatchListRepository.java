package com.moovy.repository;

import com.moovy.entity.Movie;
import com.moovy.entity.User;
import com.moovy.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

    boolean existsByUserAndMovie(User user, Movie movie);

    List<WatchList> findByUser_UserId(int userId);

    // Method to delete a watchlist entry by user ID and movie ID
    void deleteByUser_UserIdAndMovie_MovieId(int userId, int movieId);
}
