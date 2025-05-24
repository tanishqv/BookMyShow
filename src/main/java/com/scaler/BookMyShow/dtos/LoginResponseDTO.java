package com.scaler.BookMyShow.dtos;

import com.scaler.BookMyShow.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    User user;
}
