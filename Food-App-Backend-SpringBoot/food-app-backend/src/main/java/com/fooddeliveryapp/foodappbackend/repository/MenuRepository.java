package com.fooddeliveryapp.foodappbackend.repository;

import com.fooddeliveryapp.foodappbackend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByRestaurant_Id(Long id);
    void deleteByRestaurant_Id(Long id);
}
