package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.entities.UserProductKey;
import md.cernev.ProductManagment.entities.UsersProducts;
import md.cernev.ProductManagment.repository.UsersProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProductServiceImpl implements UserProductService {
    @Autowired
    private UsersProductsRepository usersProductsRepository;

    @Override
    public boolean wasBought(User user, Product product) {
        if (product.getStock() == 0 || user.getWallet() < product.getPrice()) {
            return false;
        }

        product.setStock(product.getStock() - 1);
        user.setWallet(user.getWallet() - product.getPrice());

        UserProductKey key = new UserProductKey(user.getId(), product.getId());
        Optional<UsersProducts> usersProducts = usersProductsRepository.findById(key);
        if (!usersProducts.isPresent()) {
            usersProductsRepository.save(new UsersProducts(key, user, product, 1));
        } else {
            UsersProducts products = usersProducts.get();
            products.setQuantity(products.getQuantity() + 1);
            usersProductsRepository.save(products);
        }
        return true;
    }
}
