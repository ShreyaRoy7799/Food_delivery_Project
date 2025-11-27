package com.fooddeliveryapp.foodappbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;
    private int rating;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuItem(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("rating") int rating,
                    @JsonProperty("price") Double price) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }



}
