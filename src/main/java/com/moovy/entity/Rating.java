package com.moovy.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ratingId;

    @Column()
    public int movieId;

    @Column(nullable = false)
    public int userId;

    @Column(nullable = false)
    public int rating;

    @Temporal(TemporalType.DATE)
    public Date ratingDate;

}
