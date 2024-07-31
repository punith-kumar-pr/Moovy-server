package com.moovy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenreDTO {
    private Integer movieId;
    private Boolean adult;
    private String title;
    private LocalDate releaseDate;
    private Integer runtime;
    private String tagline;
    private BigDecimal voteAverage;
    private Integer voteCount;
    private String genres;
}
