package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProducts();

    List<Product> listProducts(int userId);

    boolean saveProduct(Product product);

    boolean buy(int userId, int productId);

    boolean sell(int userId, int productId);

    boolean isExist(Product product);
}
