package com.odeofil.odeofil.model;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}
