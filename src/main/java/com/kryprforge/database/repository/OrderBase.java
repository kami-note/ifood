package com.kryprforge.database.repository;

import jakarta.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class OrderBase extends EntityBase {
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    // Getters e Setters
}
