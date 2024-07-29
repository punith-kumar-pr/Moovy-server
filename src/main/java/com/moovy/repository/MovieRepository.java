package com.moovy.repository;


import com.moovy.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> getMovieByTitle(String title);
}
