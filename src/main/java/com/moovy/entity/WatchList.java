package com.moovy.entity;

import jakarta.persistence.*;

@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    private String addedDate;

    // Getters and Setters
}
