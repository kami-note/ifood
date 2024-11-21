package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "has_pickup_option")
    private boolean hasPickupOption;
}
