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

}
