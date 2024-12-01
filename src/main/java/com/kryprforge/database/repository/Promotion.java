package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion")
public class Promotion extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "discount_value")
    private double discountValue;

    @Column(name = "code")
    private String code;

    @Column(name = "validity")
    private String validity;

    public Promotion(){
        super();
    }

    public Promotion(String name, double discountValue, String code, String validity) {
        this.name = name;
        this.discountValue = discountValue;
        this.code = code;
        this.validity = validity;
        this.setStatus(Status.ACTIVE);
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
