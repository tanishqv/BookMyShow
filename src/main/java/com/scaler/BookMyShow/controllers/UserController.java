package com.scaler.BookMyShow.controllers;

import com.scaler.BookMyShow.dtos.LoginRequestDTO;
import com.scaler.BookMyShow.dtos.LoginResponseDTO;
import com.scaler.BookMyShow.dtos.SignupRequestDTO;
import com.scaler.BookMyShow.dtos.SignupResponseDTO;
import com.scaler.BookMyShow.models.User;
import com.scaler.BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public SignupResponseDTO signUpUser(SignupRequestDTO signupRequestDTO) {
        User user = userService.signupUser(
                signupRequestDTO.getName(),
                signupRequestDTO.getAge(),
                signupRequestDTO.getEmail(),
                signupRequestDTO.getPassword()
        );
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        signupResponseDTO.setUser(user);
        return signupResponseDTO;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userService.loginUser(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser(user);
        return loginResponseDTO;
    }
}
