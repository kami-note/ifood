package com.kryprforge.database.service;

import com.kryprforge.database.repository.OrderItem;

public class GlobalInfos {
    private long currentRestaurant;
    private OrderItem currentOrder = new OrderItem();

    public long getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(long currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }
}