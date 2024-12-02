package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends OrderBase {
    @Column(name = "delivery_fee")
    private double deliveryFee;

    @Column(name = "change_amount")
    private double changeAmount;

    @Column(name = "notes")
    private String notes;

    public CustomerOrder(double deliveryFee, double changeAmount, String notes) {
        this.deliveryFee = deliveryFee;
        this.changeAmount = changeAmount;
        this.notes = notes;
        this.setStatus(Status.ACTIVE);
    }

    public CustomerOrder(){
        super();
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
