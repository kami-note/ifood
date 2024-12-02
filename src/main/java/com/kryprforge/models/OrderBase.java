package com.kryprforge.models;

import jakarta.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class OrderBase extends EntityBase {
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;
}
