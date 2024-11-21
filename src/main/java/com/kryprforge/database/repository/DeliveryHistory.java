package com.kryprforge.database.repository;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_history")
public class DeliveryHistory extends EntityBase {
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "customerorder_id", referencedColumnName = "id")
    private CustomerOrder order;

    @Column(name = "date")
    private Date date;

    // Getters and Setters
}
