package com.moovy.service.impls;

import com.moovy.dto.MovieResponseDto;
import com.moovy.entity.Genre;
import com.moovy.repository.GenreRepository;
import com.moovy.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
