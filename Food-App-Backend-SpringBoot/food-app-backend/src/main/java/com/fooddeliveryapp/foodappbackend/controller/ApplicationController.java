package com.fooddeliveryapp.foodappbackend.controller;

import com.fooddeliveryapp.foodappbackend.entity.*;
import com.fooddeliveryapp.foodappbackend.exception.RestaurantNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.UserNotFoundException;
import com.fooddeliveryapp.foodappbackend.service.ApplicationService;
import com.fooddeliveryapp.foodappbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ApplicationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home(){
        LOGGER.info("Entered Home Page");
        return "Hello world";
    }

    @PostMapping("/api/v1.0/register")
    public User processRegister(@Valid @RequestBody User user) {
        return applicationService.registration(user);
    }

//    @PostMapping("/api/v1.0/login")
//    public String userLogin(@RequestBody Login login) throws UserNotFoundException {
//        return applicationService.userLogin(login);
//    }
    @PostMapping("/api/v1.0/login")
    public LoginResponse userLogin(@RequestBody Login login) throws UserNotFoundException {
        String result = applicationService.userLogin(login);

        if (result.equals("success")) {
            return new LoginResponse("success", "Login successful");
        } else {
            return new LoginResponse("failed", "Invalid username or password");
        }
    }

    @GetMapping("/api/v1.0/users")
    public List<User> listUsers(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return listUsers;
    }

    // -----------------------------
    // RESTAURANT OPERATIONS ONLY
    // -----------------------------

    @GetMapping("/api/v1.0/foodapp/restaurants/all")
    public List<Restaurant> fetchRestaurants(){
        LOGGER.info("Returned All Restaurants");
        return applicationService.findAllRestaurant();
    }

    @GetMapping("/api/v1.0/foodapp/restaurants/{id}")
    public Restaurant findRestaurantById(@PathVariable Long id) throws RestaurantNotFoundException {
        LOGGER.info("Returned Restaurant By ID");
        return applicationService.findRestaurantById(id);
    }

    @PostMapping("/api/v1.0/admin/foodapp/restaurants/add")
    public Restaurant addRestaurant(@Valid @RequestBody Restaurant restaurant){
        LOGGER.info("Added Restaurant");
        return applicationService.addRestaurant(restaurant);
    }

    @PostMapping("/api/v1.0/admin/foodapp/restaurants/upload")
    public List<Restaurant> uploadRestaurant(@Valid @RequestBody List<Restaurant> restaurantList){
        LOGGER.info("Bulk Upload Restaurants");
        return applicationService.addBulkRestaurant(restaurantList);
    }

    @PutMapping("/api/v1.0/admin/foodapp/restaurants/update/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant)
            throws RestaurantNotFoundException {
        LOGGER.info("Updated a Restaurant");
        return applicationService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/api/v1.0/admin/foodapp/restaurants/delete/{id}")
    public String deleteRestaurantByID(@PathVariable Long id) throws RestaurantNotFoundException {
        LOGGER.info("Deleted Restaurant");
        return applicationService.deleteRestaurantById(id);
    }

    @DeleteMapping("/api/v1.0/admin/foodapp/restaurants/delete/all")
    public String deleteAllRestaurants(){
        LOGGER.info("Deleted ALL Restaurants");
        return applicationService.deleteAllRestaurant();
    }
}
