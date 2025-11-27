package com.fooddeliveryapp.foodappbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Email
    private String email;
    private String password;
}
