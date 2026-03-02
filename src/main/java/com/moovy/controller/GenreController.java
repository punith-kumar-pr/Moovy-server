package com.moovy.controller;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.Genre;
import com.moovy.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    private List<MovieResponseDto.GenreDto> getAllGenre() {
        return genreService.getAllGenres().stream()
                .map(genre -> {
                    MovieResponseDto.GenreDto genreDto = new MovieResponseDto.GenreDto();
                    genreDto.setId(genre.getGenreId());
                    genreDto.setGenreName(genre.getGenreName());
                    return genreDto;
                })
                .collect(Collectors.toList());
    }
}
