package com.moovy.repository;

import com.moovy.entity.Movie;
import com.moovy.entity.Review;
import com.moovy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findByUserAndMovie(User user, Movie movie);

    List<Review> findByUser_UserId(int userId);

    List<Review> findByMovie_MovieId(int movieId);

    void deleteByUserAndMovie(User user, Movie movie);
}
