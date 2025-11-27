package com.fooddeliveryapp.foodappbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long menuItemId;
        private String name;
        private Double price;
        private Integer quantity;
    }

    private String customerName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    private String paymentMethod;

    private List<Item> items;
}
