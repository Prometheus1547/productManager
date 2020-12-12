package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserProductService userProductService;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listProducts(int userId) {
        Optional<User> user = userService.getUser(userId);
        Set<User> users = new HashSet<>();
        user.ifPresent(users::add);
        return productRepository.findByUsersIn(users);
    }

    @Override
    public boolean saveProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean buy(int userId, int productId) {
        Optional<User> optionalUser = userService.getUser(userId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalUser.isPresent() || !optionalProduct.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        Product product = optionalProduct.get();


        return userProductService.wasBought(user, product);
    }

    @Override
    public boolean isExist(Product product) {
        return productRepository.findById(product.getId()).isPresent() || productRepository.findByName(product.getName()).isPresent();
    }

}
