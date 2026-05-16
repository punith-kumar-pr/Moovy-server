package com.moovy.repository;

import com.moovy.entity.MovieCast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, Integer> {
    List<MovieCast> findByMovie_MovieIdOrderByOrderAsc(Integer movieId);
}
