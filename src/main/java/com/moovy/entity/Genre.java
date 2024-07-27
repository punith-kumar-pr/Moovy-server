package com.moovy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name", nullable = false)
    private String genreName;

}

