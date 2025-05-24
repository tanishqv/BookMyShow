package com.scaler.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private Integer age;
    private String email;
    private String password;
}
