package com.scaler.BookMyShow.controllers;

import com.scaler.BookMyShow.dtos.LoginRequestDTO;
import com.scaler.BookMyShow.dtos.LoginResponseDTO;
import com.scaler.BookMyShow.dtos.SignupRequestDTO;
import com.scaler.BookMyShow.dtos.SignupResponseDTO;
import com.scaler.BookMyShow.exceptions.PasswordMismatchException;
import com.scaler.BookMyShow.exceptions.UserAlreadyExistsException;
import com.scaler.BookMyShow.exceptions.UserNotFoundException;
import com.scaler.BookMyShow.models.User;
import com.scaler.BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public SignupResponseDTO signUpUser(SignupRequestDTO signupRequestDTO) {
        User user;
        try {
            user = userService.signupUser(
                    signupRequestDTO.getName(),
                    signupRequestDTO.getAge(),
                    signupRequestDTO.getEmail(),
                    signupRequestDTO.getPassword()
            );
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            user = null;
        }
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        signupResponseDTO.setUser(user);
        return signupResponseDTO;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        User user;
        try {
            user = userService.loginUser(
                    loginRequestDTO.getEmail(),
                    loginRequestDTO.getPassword()
            );
        } catch (UserNotFoundException | PasswordMismatchException runtimeException) {
            user = null;
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser(user);
        return loginResponseDTO;
    }
}
