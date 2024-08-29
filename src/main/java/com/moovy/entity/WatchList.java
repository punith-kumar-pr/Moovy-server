package com.moovy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "watch_list",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "movie_id"})}) // Ensure uniqueness
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Surrogate key

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Assume User entity is already defined

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchList that = (WatchList) o;
        return Objects.equals(id, that.id);
    }
}
