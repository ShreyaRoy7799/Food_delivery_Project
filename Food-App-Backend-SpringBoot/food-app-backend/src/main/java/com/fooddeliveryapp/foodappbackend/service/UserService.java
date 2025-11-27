package com.fooddeliveryapp.foodappbackend.service;

import com.fooddeliveryapp.foodappbackend.entity.Role;
import com.fooddeliveryapp.foodappbackend.entity.User;
import com.fooddeliveryapp.foodappbackend.repository.RoleRepository;
import com.fooddeliveryapp.foodappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;


    public void registerDefaultUser(User user) {
        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }
    public List<User> listAll() {
        return userRepo.findAll();
    }
}
