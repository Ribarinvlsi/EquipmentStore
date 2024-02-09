package com.udhtu.model.dto;

import java.util.Objects;

public class ProductDto
        extends BasedDto<Long> {

    private String productName;
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

    // region equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productName, productPrice);
    }
    // endregion
}
