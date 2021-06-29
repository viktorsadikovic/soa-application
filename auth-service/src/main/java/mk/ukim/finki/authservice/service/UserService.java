package mk.ukim.finki.authservice.service;

import mk.ukim.finki.authservice.model.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User register(User user);
}
