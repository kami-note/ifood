package com.kryprforge.models;

public enum CategoryType {
    RESTAURANT(1),
    FOOD(0),
    DRINK(-1);

    private final int value;

    CategoryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
