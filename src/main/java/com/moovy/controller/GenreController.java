package com.moovy.controller;

import com.moovy.entity.Genre;
import com.moovy.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    private List<Genre> getAllGenre() {
        return  genreService.getAllGenre();
    }
}
