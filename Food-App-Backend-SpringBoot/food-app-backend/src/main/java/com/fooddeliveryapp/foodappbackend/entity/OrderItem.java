package com.fooddeliveryapp.foodappbackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long menuItemId;     // original MenuItem id (optional)
    private String name;         // snapshot of item name
    private Double price;        // snapshot of price
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

}
