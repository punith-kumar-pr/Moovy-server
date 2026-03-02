package com.moovy.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieResponseDto {
    private Integer id;
    private String title;
    private BigDecimal voteAverage;
    private Integer voteCount;
    private String summary;
    private Boolean adult;
    private String imageUrl;
    private LocalDate releaseDate;
    private Integer runtime;
    private String tagline;
    private String trailerUrl;
    private List<GenreDto> genres;

    @Data
    public static class GenreDto {
        private Integer id;
        private String genreName;
    }
}