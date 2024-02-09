package com.udhtu.model.dto;

import javax.xml.crypto.Data;
import java.util.Objects;

public class ReceiptDto
        extends BasedDto<Long> {
    private ClientDto client;
    private ProductDto product;
    private String purchaseDate;

    // region get, set
    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    // endregion

    // region equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReceiptDto that = (ReceiptDto) o;
        return Objects.equals(client, that.client) && Objects.equals(product, that.product) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), client, product, purchaseDate);
    }
    // endregion
}
