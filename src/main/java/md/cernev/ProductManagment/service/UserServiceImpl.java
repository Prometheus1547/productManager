package md.cernev.ProductManagment.service;

import md.cernev.ProductManagment.entities.User;
import md.cernev.ProductManagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
