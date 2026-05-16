package com.moovy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MovieCast> movieCasts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MovieCrew> movieCrews;
}
