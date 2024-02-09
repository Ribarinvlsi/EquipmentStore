package com.udhtu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity
        extends BasedEntity<Long> {

    @Column(name = "product_name", length = 50)
    private String productName;
    @Column(name = "product_price", precision = 6, scale = 2)
    private Float productPrice;

    // region get, set
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }
    // endregion
}
