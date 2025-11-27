package com.fooddeliveryapp.foodappbackend.repository;

import com.fooddeliveryapp.foodappbackend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // Get all menus for a specific restaurant
    List<Menu> findByRestaurantId(Long restaurantId);

	void deleteByRestaurant_Id(Long res_id);

	List<Menu> findByRestaurant_Id(Long id);
}
