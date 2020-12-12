package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> list();

    Optional<User> getUser(int id);

    Optional<User> getUser(String login);

    boolean saveUser(User user);
}
