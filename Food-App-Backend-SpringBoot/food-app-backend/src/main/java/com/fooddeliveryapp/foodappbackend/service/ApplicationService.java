package com.fooddeliveryapp.foodappbackend.service;

import com.fooddeliveryapp.foodappbackend.entity.*;
import com.fooddeliveryapp.foodappbackend.exception.MenuItemNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.MenuNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.RestaurantNotFoundException;
import com.fooddeliveryapp.foodappbackend.exception.UserNotFoundException;
import com.fooddeliveryapp.foodappbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
//
    @Autowired
    private UserRepository userRepository;
    //find all the food in the menu
    public List<MenuItem> findAllFood(){
        return menuItemRepository.findAll();
    }

    public Menu findMenuById(Long id) throws MenuNotFoundException {
        Optional<Menu> menu =  menuRepository.findById(id);
        if(!menu.isPresent()){
            throw new MenuNotFoundException("Menu Not found in the Database");
        }
        return menu.get();
    }

    public MenuItem editMenu(String name, MenuItem menuItem) throws MenuItemNotFoundException {
        MenuItem menuItemDb = findMenuItemByName(name);
        if(Objects.nonNull(String.valueOf(menuItemDb.getName())) &&
                !"".equalsIgnoreCase(String.valueOf(menuItem.getName()))){
            menuItemDb.setName(menuItem.getName());
        }
        if(Objects.nonNull(String.valueOf(menuItemDb.getRating())) &&
                !"".equalsIgnoreCase(String.valueOf(menuItem.getRating()))){
            menuItemDb.setRating(menuItem.getRating());
        }
        if(Objects.nonNull(String.valueOf(menuItemDb.getPrice())) &&
                !"".equalsIgnoreCase(String.valueOf(menuItem.getPrice()))){
            menuItemDb.setPrice(menuItem.getPrice());
        }
        menuItemRepository.save(menuItemDb);
        return menuItemDb;
    }
    public MenuItem editRating(String name,MenuItem menuItem) throws MenuItemNotFoundException {
        MenuItem menuItemDb = findMenuItemByName(name);
        if(Objects.nonNull(String.valueOf(menuItemDb.getRating())) &&
                    !"".equalsIgnoreCase(String.valueOf(menuItem.getRating()))){
                menuItemDb.setRating(menuItem.getRating());
            }
        menuItemRepository.save(menuItemDb);
        return menuItemDb;
    }

    public MenuItem findMenuItemByName(String name) throws MenuItemNotFoundException {
        Optional<MenuItem> menuItem = menuItemRepository.findByName(name);
        if(!menuItem.isPresent()){
            throw new MenuItemNotFoundException("Menu Item not found in the Database");
        }
        return menuItem.get();
    }

    //find all registered restaurant
    public List<Restaurant> findAllRestaurant(){
        return restaurantRepository.findAll();
    }

    //To upload a bulk amount of restaurants at once
    public List<Restaurant> addBulkRestaurant(List<Restaurant> restaurants){
        restaurantRepository.saveAll(restaurants);
        return restaurants;
    }

    //To add a restaurants
    public Restaurant addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant findRestaurantById(Long id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()){
            throw new RestaurantNotFoundException("Restaurant not found in the Database");
        }
        return restaurant.get();
    }

    //To delete all restaurants
    public String deleteAllRestaurant(){
        restaurantRepository.deleteAll();
        return "All restaurants removed from the DB.";
    }

    //To delete a particular restaurant
    public String deleteRestaurantById(Long id) throws RestaurantNotFoundException {
        String name = findRestaurantById(id).getName();
        restaurantRepository.deleteById(id);
        return name + "restaurant is successfully deleted";
    }

    //Get menu of the particular restaurant
    public List<Menu> getMenuByRestaurantId(Long id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (!restaurant.isPresent()){
            throw new RestaurantNotFoundException("Restaurant not Found in the Database");
        }
        return menuRepository.findByRestaurant_Id(id);
    }

    //add menus to the restaurants
    public void addMenus(@PathVariable("id") Long id, @RequestBody List<Menu> menus) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()){
            throw new RestaurantNotFoundException("Restaurant not found in the database");
        }
        for(Menu menu:menus) {
            menu.setRestaurant(restaurant.get());
        }
    }

    //delete menus from the restaurants
    public void deleteMenu(Long res_id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(res_id);
        if(!restaurant.isPresent()){
            throw new RestaurantNotFoundException("Restaurant not Found in the Database");
        }
        menuRepository.deleteByRestaurant_Id(res_id);
    }

    //update Restaurant
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantcheck = restaurantRepository.findById(id);
        if(!restaurantcheck.isPresent()){
            throw new RestaurantNotFoundException("Restaurant not Found in the Database");
        }
        Restaurant restaurantdb = restaurantcheck.get();
        if(Objects.nonNull(restaurantdb.getName()) &&
                !"".equalsIgnoreCase(restaurant.getName())){
            restaurantdb.setName(restaurant.getName());
        }
//        restaurantdb.setMenus(restaurant.getMenus());
        return restaurantRepository.save(restaurantdb);
    }


    public User registration(User user) {
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setEmail(user.getEmail());
        user1.setLastName(user.getLastName());
        user1.setPassword(user.getPassword());
//        user1.setRoles(user.getRoles());
        return userRepository.save(user1);
    }

    public String userLogin(Login login) throws UserNotFoundException {
        Optional<User> userCheck = userRepository.findByEmail(login.getEmail());

        if (userCheck.isPresent()) {
            User userDB = userCheck.get();

            if (userDB.getEmail().equals(login.getEmail()) &&
                userDB.getPassword().equals(login.getPassword())) {
                return "success";
            }
            return "failed";
        }
        throw new UserNotFoundException("This Email id is not registered in the database");
    }

}
