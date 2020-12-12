package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;

public interface UserProductService {
    boolean wasBought(User user, Product product);
}
