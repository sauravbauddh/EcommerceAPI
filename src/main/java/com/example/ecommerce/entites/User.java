package com.example.ecommerce.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
