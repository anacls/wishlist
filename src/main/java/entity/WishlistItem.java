package entity;

import javax.persistence.*;

@Entity
@Table(name = "wishlist", uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "product_id"})})
public class WishlistItem {
    @Id
    @GeneratedValue
    private int id;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="product_id")
    private String productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
