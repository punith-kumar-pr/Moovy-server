package com.moovy.dto;

import lombok.*;

import java.math.BigDecimal;
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
    private List<GenreDto> genres;

    @Data
    public static class GenreDto {
        private Integer id;
        private String genreName;
    }
}