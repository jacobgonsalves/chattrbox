package com.example.demo.service;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        userRepository.insert(user);
        return "[" + user.getUserEmail() + "] created.";
    }

    public User findUser(String email) throws UserNotFoundException {

        Optional<User> user = userRepository.findByUserEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException("No user found for email: " + email));
    }

    public String updateUserEmail(String email, String updatedEmail) {

        Optional<User> user = userRepository.findByUserEmail(email);
        user.ifPresent(u -> {
            u.setUserEmail(updatedEmail);
            userRepository.save(u);
        });
        return "[" + updatedEmail + "] updated.";
    }

    public String deleteUser(String email) {
        userRepository.deleteByUserEmail(email);
        return "[" + email + "] deleted.";
    }

    public String deleteRepository() {
        userRepository.deleteAll();
        return "Clearing user repository...";
    }
}
