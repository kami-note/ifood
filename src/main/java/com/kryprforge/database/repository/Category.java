package com.kryprforge.database.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends EntityBase {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
