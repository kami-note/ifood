package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "product_accompaniment")
public class ProductAccompaniment extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "accompaniment_id", referencedColumnName = "id")
    private Accompaniment accompaniment;
}
