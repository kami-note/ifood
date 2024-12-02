package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "has_pickup_option")
    private boolean hasPickupOption;

    public Restaurant(){
        super();
    }

    public Restaurant(String name, String phoneNumber, Category category, boolean hasPickupOption) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.hasPickupOption = hasPickupOption;
        setStatus(Status.ACTIVE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isHasPickupOption() {
        return hasPickupOption;
    }

    public void setHasPickupOption(boolean hasPickupOption) {
        this.hasPickupOption = hasPickupOption;
    }
}
