package com.kryprforge.database.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends EntityBase {

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(name = "address_type", nullable = false)
    private String addressType;

    public Address() {}

    public Address(String street, String number, String neighborhood, String city, String state, String postalCode, String addressType) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
