package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();

    User show(Long id);

    User showByEmail(String email);

    void save(User user);

    void update(Long id, User user);

    void delete(Long id);
}
