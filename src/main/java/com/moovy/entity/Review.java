package com.moovy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int reviewId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @Column(columnDefinition = "TEXT")
    public String reviewText;

    @Temporal(TemporalType.DATE)
    public Date reviewDate;

}