package com.moovy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "movie_id"})})  // Ensures a user can rate each movie only once
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;  // Surrogate key

    @Column(name = "rating", nullable = false)
    @Min(1)
    @Max(10)
    /* --- >do this in Database to add constaint in Database level
    ALTER TABLE rating
    ADD CONSTRAINT check_rating_range CHECK (rating BETWEEN 1 AND 10);*/
    private Integer rating;

    @Column(name = "rating_date")
    private LocalDate ratingDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Foreign key to User entity

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;  // Foreign key to Movie entity

    @Override
    public int hashCode() {
        return Objects.hash(ratingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(ratingId, rating.ratingId);
    }
}
