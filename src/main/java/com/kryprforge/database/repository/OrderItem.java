package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    // Getters e Setters
}
