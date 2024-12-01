package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
