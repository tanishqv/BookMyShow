package com.scaler.BookMyShow.services;

import com.scaler.BookMyShow.exceptions.PasswordMismatchException;
import com.scaler.BookMyShow.exceptions.UserAlreadyExistsException;
import com.scaler.BookMyShow.exceptions.UserNotFoundException;
import com.scaler.BookMyShow.models.User;
import com.scaler.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signupUser(String name, Integer age, String email, String password) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        if (userOptional.isEmpty()) {
            user = new User();
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            user = userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("email already exists");
        }
        return user;
    }

    public User loginUser(String email, String password) throws UserNotFoundException, PasswordMismatchException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user does not exist");
        } else {
            if (!userOptional.get().getPassword().equals(password)) {
                throw new PasswordMismatchException("password incorrect");
            }
            return userOptional.get();
        }
    }
}
