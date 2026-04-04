package com.moovy.repository;

import com.moovy.entity.Movie;
import com.moovy.entity.Rating;
import com.moovy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByUserAndMovie(User user, Movie movie);

    Optional<Rating> findByUserAndMovie(User user, Movie movie);

    List<Rating> findByUser_UserId(int userId);

    List<Rating> findByMovie_MovieId(int movieId);

    void deleteByUserAndMovie(User user, Movie movie);
}
