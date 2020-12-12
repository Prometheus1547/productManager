package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.entities.UserProductKey;
import md.cernev.ProductManagment.entities.UsersProducts;
import md.cernev.ProductManagment.repository.UsersProductsRepository;
import md.cernev.ProductManagment.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProductServiceImpl implements UserProductService {
    private final Logger log = LoggerFactory.getLogger(UserProductServiceImpl.class);
    @Autowired
    private UsersProductsRepository usersProductsRepository;

    @Override
    public boolean wasBought(User user, Product product) {
        if (product.getStock() == 0 || user.getWallet() < product.getPrice()) {
            log.warn("Product {} is out of stock({}) or user(ID={}, login={}) has not enough money({}).", product.getName(), product.getStock(), user.getId(), user.getLogin(), user.getWallet());
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
        log.info("User(ID={}, login={}) bought product \"{}\" with success.", user.getId(), user.getLogin(), product.getName());
        return true;
    }

    @Override
    public boolean wasSold(User user, Product product) {
        UserProductKey key = new UserProductKey(user.getId(), product.getId());
        Optional<UsersProducts> usersProducts = usersProductsRepository.findById(key);
        if (!usersProducts.isPresent()) {
            log.warn("User '{}' does not have products '{}'.", user.getLogin(), product.getName());
            return false;
        } else {
            UsersProducts products = usersProducts.get();
            if (products.getQuantity() == 0) {
                log.warn("User '{}' does not have products '{}'.", user.getLogin(), product.getName());
                return false;
            } else {
                product.setStock(product.getStock() + 1);
                user.setWallet(user.getWallet() + product.getPrice() * Utils.SALES_RATIO);
                products.setQuantity(products.getQuantity() - 1);
                usersProductsRepository.save(products);
            }
        }
        log.info("User(ID={}, login={}) sold product \"{}\" with success.", user.getId(), user.getLogin(), product.getName());
        return true;
    }
}
