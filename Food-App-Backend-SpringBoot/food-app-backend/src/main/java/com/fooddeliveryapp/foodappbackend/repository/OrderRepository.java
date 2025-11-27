package com.fooddeliveryapp.foodappbackend.repository;

import com.fooddeliveryapp.foodappbackend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
