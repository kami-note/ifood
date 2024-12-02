package com.kryprforge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_accompaniment")
public class ProductAccompaniment extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "accompaniment_id", referencedColumnName = "id")
    private Accompaniment accompaniment;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Accompaniment getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(Accompaniment accompaniment) {
        this.accompaniment = accompaniment;
    }
}
