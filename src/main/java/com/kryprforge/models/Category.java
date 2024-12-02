package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category extends EntityBase {
    @Enumerated(EnumType.STRING)
    @Column(name = "category_type")
    private CategoryType category_type;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Category(CategoryType category_type, String name, String description) {
        this.category_type = category_type;
        this.name = name;
        this.description = description;
        this.setStatus(Status.ACTIVE);
    }

    public Category(){
        super();
    }

    public CategoryType getCategory_type() {
        return category_type;
    }

    public void setCategory_type(CategoryType category_type) {
        this.category_type = category_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
