package com.udhtu.model.entity;
import javax.persistence.*;

@Entity
@Table(name = "receipts")
public class ReceiptEntity
        extends BasedEntity<Long> {

    @OneToOne(targetEntity = ClientEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @OneToOne(targetEntity = ProductEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @Column(name = "purchase_date", length = 50)
    private String purchaseDate;

    // region get, set
    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    // endregion
}
