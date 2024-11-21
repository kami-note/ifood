package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion")
public class Promotion extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "code")
    private String code;

    @Column(name = "validity")
    private String validity;

    // Getters and Setters
}
