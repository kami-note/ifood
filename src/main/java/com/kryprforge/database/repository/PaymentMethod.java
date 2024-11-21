package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethod extends EntityBase {
    @Column(name = "payment_type")
    private String paymentType;

    // Getters and Setters
}
