package com.kryprforge.database.repository;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_history")
public class PaymentHistory extends EntityBase {
    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @Column(name = "amount")
    private double amount;

    // Getters and Setters
}
