package com.fooddeliveryapp.foodappbackend.repository;

import com.fooddeliveryapp.foodappbackend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {}
