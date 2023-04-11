package vija.tieto.bookstore.service;

import vija.tieto.bookstore.model.User;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    Optional<User> findByLogin(String login);

    boolean isAdmin(Principal principal);

    User saveUser(User user);
}
