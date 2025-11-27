package com.fooddeliveryapp.foodappbackend.controller;

import com.fooddeliveryapp.foodappbackend.entity.*;
import com.fooddeliveryapp.foodappbackend.exception.MenuItemNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.MenuNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.RestaurantNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.UserNotFoundException;
import com.fooddeliveryapp.foodappbackend.repository.MenuItemRepository;
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
import java.util.Objects;


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

    @PostMapping("/api/v1.0/login")
    public String userLogin(@Valid @RequestBody Login login) throws UserNotFoundException {
       return applicationService.userLogin(login);
    }
    @GetMapping("/api/v1.0/users")
    public List<User> listUsers(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return listUsers;
    }




    //Controller for Menu related uses
    @GetMapping(value = "/api/v1.0/foodapp/food/all")
    public List<MenuItem> fetchMenus() {
        LOGGER.info("Returned All food in the Database");
        return applicationService.findAllFood();
    }

    @GetMapping(value = "/api/v1.0/foodapp/food/{id}")
    public Menu findMenuById(@PathVariable("id") Long id) throws MenuNotFoundException {
        LOGGER.info("Returned Food By its ID");
        return applicationService.findMenuById(id);
    }
    @PutMapping(value = "/api/v1.0/foodapp/admin/food/{name}/edit")
    public MenuItem editMenu(@PathVariable("name") String name,@RequestBody MenuItem menuItem) throws MenuItemNotFoundException {
        LOGGER.info("Edited the menu");
        return applicationService.editMenu(name,menuItem);
    }

    @PutMapping(value = "/api/v1.0/foodapp/food/{name}/rating/")
    public MenuItem editRating(@PathVariable("name") String name,@RequestBody MenuItem menuItem) throws MenuItemNotFoundException {
        LOGGER.info("Edited the Rating of the food");
        return applicationService.editRating(name,menuItem);
    }

    //Controller for Restaurant uses

    @GetMapping(value = "/api/v1.0/foodapp/restaurants/all")
    public List<Restaurant> fetchRestaurants(){
        LOGGER.info("Returned All the Restaurant from the Database");
        return applicationService.findAllRestaurant();
    }

    @GetMapping(value = "/api/v1.0/foodapp/restaurants/{id}")
    public Restaurant findRestaurantById(@PathVariable("id") Long id) throws RestaurantNotFoundException {
        LOGGER.info("Returned Restaurant By its ID");
        return applicationService.findRestaurantById(id);
    }

    @PostMapping(value = "/api/v1.0/admin/foodapp/restaurants/add")
    public Restaurant addRestaurant(@Valid @RequestBody Restaurant restaurant){
        LOGGER.info("Inside Add Restaurant method");
        return applicationService.addRestaurant(restaurant);
    }

    @PostMapping(value = "/api/v1.0/admin/foodapp/restaurants/upload")
    public List<Restaurant> uploadRestaurant(@Valid @RequestBody List<Restaurant> restaurantList){
        LOGGER.info("Uploaded more than one Restaurant at a time");
        return applicationService.addBulkRestaurant(restaurantList);
    }

    @PutMapping(value = "/api/v1.0/admin/foodapp/restaurants/update/{id}")
    public Restaurant updateRestaurant(@PathVariable("id") Long id,@RequestBody Restaurant restaurant) throws RestaurantNotFoundException {
        LOGGER.info("Updated a Restaurant");
        return applicationService.updateRestaurant(id,restaurant);
    }


    @DeleteMapping("/api/v1.0/admin/foodapp/restaurants/delete/{id}")
    public String deleteRestaurantByID(@PathVariable("id") Long id) throws RestaurantNotFoundException {
        LOGGER.info("Deleted a Restaurant");
        return applicationService.deleteRestaurantById(id);
    }
    @DeleteMapping("/api/v1.0/admin/foodapp/restaurants/delete/all")
    public String deleteAllRestaurants(){
        LOGGER.info("Delete All Restaurant");
        return applicationService.deleteAllRestaurant();
    }
}
