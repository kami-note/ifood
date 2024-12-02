package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating extends EntityBase {
    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "customerorder_id", referencedColumnName = "id")
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
}
