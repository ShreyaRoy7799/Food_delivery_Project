package com.fooddeliveryapp.foodappbackend.controller;

import com.fooddeliveryapp.foodappbackend.dto.CreateOrderRequest;
import com.fooddeliveryapp.foodappbackend.entity.OrderEntity;
import com.fooddeliveryapp.foodappbackend.entity.OrderItem;
import com.fooddeliveryapp.foodappbackend.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/place")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody CreateOrderRequest req) {

        OrderEntity order = new OrderEntity();
        order.setCustomerName(req.getCustomerName());
        order.setPhone(req.getPhone());
        order.setAddressLine1(req.getAddressLine1());
        order.setAddressLine2(req.getAddressLine2());
        order.setCity(req.getCity());
        order.setState(req.getState());
        order.setPincode(req.getPincode());
        order.setPaymentMethod(req.getPaymentMethod() == null ? "COD" : req.getPaymentMethod());
        order.setStatus("PLACED");

        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;

        if (req.getItems() != null) {
            for (CreateOrderRequest.Item it : req.getItems()) {
                OrderItem oi = new OrderItem();
                oi.setMenuItemId(it.getMenuItemId());
                oi.setName(it.getName());
                oi.setPrice(it.getPrice());
                oi.setQuantity(it.getQuantity());
                oi.setOrder(order);
                items.add(oi);

                total += (it.getPrice() != null ? it.getPrice() : 0.0) *
                         (it.getQuantity() != null ? it.getQuantity() : 1);
            }
        }

        order.setItems(items);
        order.setTotalAmount(total);

        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ CLEAN PATCH METHOD (Only ONE)
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderEntity> updateStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> body) {

        String status = body.get("status");

        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setStatus(status.toUpperCase());
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/delivered")
    public List<OrderEntity> getDeliveredOrders() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .toList();
    }
}
