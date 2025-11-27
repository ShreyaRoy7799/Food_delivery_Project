package com.fooddeliveryapp.foodappbackend.controller;

import com.fooddeliveryapp.foodappbackend.entity.Menu;
import com.fooddeliveryapp.foodappbackend.entity.MenuItem;
import com.fooddeliveryapp.foodappbackend.entity.Restaurant;
import com.fooddeliveryapp.foodappbackend.repository.MenuItemRepository;
import com.fooddeliveryapp.foodappbackend.repository.MenuRepository;
import com.fooddeliveryapp.foodappbackend.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuController(MenuRepository menuRepository,
                          MenuItemRepository menuItemRepository,
                          RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // ------------------------------------
    // ADD NEW MENU TO RESTAURANT
    // ------------------------------------
    @PostMapping("/add/{restaurantId}")
    public Menu addMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    // ------------------------------------
    // ADD ITEM TO MENU
    // ------------------------------------
    @PostMapping("/item/add/{menuId}")
    public MenuItem addMenuItem(@PathVariable Long menuId, @RequestBody MenuItem item) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        item.setMenu(menu);
        return menuItemRepository.save(item);
    }

    // ------------------------------------
    // GET ALL MENUS FOR RESTAURANT
    // ------------------------------------
    @GetMapping("/restaurant/{restaurantId}")
    public List<Menu> getMenusByRestaurant(@PathVariable Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    // ------------------------------------
    // GET ITEMS FOR MENU
    // ------------------------------------
    @GetMapping("/items/{menuId}")
    public List<MenuItem> getItemsByMenu(@PathVariable Long menuId) {
        return menuItemRepository.findByMenuId(menuId);
    }

    // ------------------------------------
    // UPDATE MENU
    // ------------------------------------
    @PutMapping("/update/{menuId}")
    public Menu updateMenu(@PathVariable Long menuId, @RequestBody Menu menuData) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setName(menuData.getName());
        menu.setType(menuData.getType());

        return menuRepository.save(menu);
    }

    // ------------------------------------
    // DELETE MENU
    // ------------------------------------
    @DeleteMapping("/delete/{menuId}")
    public String deleteMenu(@PathVariable Long menuId) {
        menuRepository.deleteById(menuId);
        return "Menu deleted";
    }

    // ------------------------------------
    // DELETE MENU ITEM
    // ------------------------------------
    @DeleteMapping("/item/delete/{itemId}")
    public String deleteMenuItem(@PathVariable Long itemId) {
        menuItemRepository.deleteById(itemId);
        return "Menu item deleted";
    }
}
