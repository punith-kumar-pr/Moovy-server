package com.moovy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "Actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column
    private Date birthdate;

    @Column(length = 100)
    private String nationality;
}