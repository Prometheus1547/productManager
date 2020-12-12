package md.cernev.ProductManagment.repository;

import md.cernev.ProductManagment.entities.UserProductKey;
import md.cernev.ProductManagment.entities.UsersProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProductsRepository extends JpaRepository<UsersProducts, UserProductKey> {
}
