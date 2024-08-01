package com.moovy.entity;

import com.moovy.dtos.MovieGenreDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movie")
@SqlResultSetMapping(
        name = "MovieGenreDTOMapping",
        classes = @ConstructorResult(
                targetClass = MovieGenreDTO.class,
                columns = {
                        @ColumnResult(name = "movieId", type = Integer.class),
                        @ColumnResult(name = "adult", type = Boolean.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "releaseDate", type = LocalDate.class),
                        @ColumnResult(name = "runtime", type = Integer.class),
                        @ColumnResult(name = "tagline", type = String.class),
                        @ColumnResult(name = "voteAverage", type = BigDecimal.class),
                        @ColumnResult(name = "voteCount", type = Integer.class),
                        @ColumnResult(name = "genres", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "Movie.findMoviesByGenreName",
        query = "SELECT m.movie_id AS movieId, m.adult AS adult, m.title AS title, m.release_date AS releaseDate, m.runtime AS runtime, m.tagline AS tagline, m.vote_average AS voteAverage, m.vote_count AS voteCount, " +
                "GROUP_CONCAT(DISTINCT g.genre_name ORDER BY g.genre_name ASC SEPARATOR ', ') AS genres " +
                "FROM movie m " +
                "JOIN moviegenre mg ON m.movie_id = mg.movie_id " +
                "JOIN genre g ON g.genre_id = mg.genre_id " +
                "GROUP BY m.movie_id " +
                "HAVING SUM(g.genre_name = :genreName) > 0",
        resultSetMapping = "MovieGenreDTOMapping"
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "adult")
    private Boolean adult;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "vote_average", precision = 3, scale = 1)
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

//    @ManyToMany ( cascade = { CascadeType.ALL })
//    @JoinTable (
//            name = "moviegenres",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
//    private Set<Genre> genres;
}
