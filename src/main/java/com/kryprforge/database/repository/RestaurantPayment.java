package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_payment")
public class RestaurantPayment extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;
}
