package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "accompaniment")
public class Accompaniment extends EntityBase {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
}
