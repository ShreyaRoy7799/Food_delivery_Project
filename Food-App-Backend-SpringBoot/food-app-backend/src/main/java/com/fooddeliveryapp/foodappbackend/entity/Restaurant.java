package com.fooddeliveryapp.foodappbackend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor

public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Menu> menus;

    @JsonCreator
    public Restaurant(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("menus") List<Menu> menus) {
        this.name = name;
        if (menus != null ) {
            this.menus = menus;
            for (Menu menu : menus)
                menu.setRestaurant(this);
        }
    }

}
