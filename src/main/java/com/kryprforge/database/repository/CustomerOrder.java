package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends OrderBase {
    @Column(name = "delivery_fee")
    private double deliveryFee;

    @Column(name = "change_amount")
    private double changeAmount;

    @Column(name = "notes")
    private String notes;

    // Getters e Setters
}
