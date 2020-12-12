package md.cernev.ProductManagment.repository;

import md.cernev.ProductManagment.entities.Product;
import md.cernev.ProductManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    List<Product> findByUsersIn(Set<User> users);
}
