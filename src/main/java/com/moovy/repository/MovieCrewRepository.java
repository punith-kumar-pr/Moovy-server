package com.moovy.repository;

import com.moovy.entity.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCrewRepository extends JpaRepository<MovieCrew, Integer> {
    List<MovieCrew> findByMovie_MovieId(Integer movieId);
}
