package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product_accompaniment")
public class OrderProductAccompaniment extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "accompaniment_id", referencedColumnName = "id")
    private Accompaniment accompaniment;

    public OrderProductAccompaniment() {
        this.setStatus(Status.ACTIVE);
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Accompaniment getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(Accompaniment accompaniment) {
        this.accompaniment = accompaniment;
    }
}
