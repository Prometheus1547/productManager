package md.cernev.ProductManagment.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserProductKey implements Serializable {
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    public UserProductKey(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public UserProductKey() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
