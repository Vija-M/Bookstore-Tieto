package vija.tieto.bookstore.service.impl;

import org.springframework.stereotype.Service;
import vija.tieto.bookstore.model.User;
import vija.tieto.bookstore.repository.UserRepository;
import vija.tieto.bookstore.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean isAdmin(Principal principal) {
        if (principal == null) {
            return false;
        }
        String login = principal.getName();
        Optional<User> user = findByLogin(login);
        return user.isPresent() && user.get().isAdmin();
    }

}
