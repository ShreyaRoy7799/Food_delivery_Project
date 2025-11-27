package com.fooddeliveryapp.foodappbackend.repository;

import com.fooddeliveryapp.foodappbackend.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    List<MenuItem> findByMenu_Id(Long id);
    Optional<MenuItem> findByName(String name);
}
