package com.fooddeliveryapp.foodappbackend.controller;

import com.fooddeliveryapp.foodappbackend.entity.OrderEntity;
import com.fooddeliveryapp.foodappbackend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminDashboardController {

    private final OrderRepository orderRepository;

    public AdminDashboardController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Map<String, Object> getDashboardStats() {

        List<OrderEntity> orders = orderRepository.findAll();

        int totalOrders = orders.size();
        double totalRevenue = orders.stream().mapToDouble(OrderEntity::getTotalAmount).sum();

        long todayOrders = orders.stream()
                .filter(o -> o.getCreatedAt().toLocalDate().isEqual(LocalDate.now()))
                .count();

        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("PLACED", orders.stream().filter(o -> "PLACED".equals(o.getStatus())).count());
        statusCounts.put("CONFIRMED", orders.stream().filter(o -> "CONFIRMED".equals(o.getStatus())).count());
        statusCounts.put("OUT_FOR_DELIVERY", orders.stream().filter(o -> "OUT_FOR_DELIVERY".equals(o.getStatus())).count());
        statusCounts.put("DELIVERED", orders.stream().filter(o -> "DELIVERED".equals(o.getStatus())).count());

        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", totalOrders);
        response.put("totalRevenue", totalRevenue);
        response.put("todayOrders", todayOrders);
        response.put("statusCounts", statusCounts);

        return response;
    }
}
