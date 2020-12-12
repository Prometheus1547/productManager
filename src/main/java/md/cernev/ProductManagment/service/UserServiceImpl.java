package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUser(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public boolean saveUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()).isPresent()) {
            log.warn("User with login '{}' already exists.", user.getLogin());
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Successful saved user '{}'.", user.getLogin());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(s);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user.get();
    }
}
