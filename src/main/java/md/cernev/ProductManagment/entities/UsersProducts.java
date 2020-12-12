package md.cernev.ProductManagment.entities;

import md.cernev.ProductManagment.utils.Utils;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "USERS_PRODUCTS")
public class UsersProducts {
    @EmbeddedId
    private UserProductKey key;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;

    public UsersProducts() {
    }


    public UsersProducts(UserProductKey key, User user, Product product, int quantity) {
        this.key = key;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public UserProductKey getKey() {
        return key;
    }

    public void setKey(UserProductKey key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSailingPrice() {
        return product.getPrice() * Utils.SALES_RATIO;
    }


}
