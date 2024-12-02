package com.kryprforge.models;

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

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
