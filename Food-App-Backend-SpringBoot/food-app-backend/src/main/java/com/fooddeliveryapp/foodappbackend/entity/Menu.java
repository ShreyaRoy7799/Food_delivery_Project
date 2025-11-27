package com.fooddeliveryapp.foodappbackend.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MenuItem> items;

    @JsonSetter
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonGetter
    public Restaurant getRestaurant(){
        return restaurant;
    }

    public Menu(Restaurant restaurant, Long id, String name, String type) {
        this.restaurant = restaurant;
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @JsonCreator
    public Menu(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("type") String type,@JsonProperty("items") List<MenuItem> items){
        this.id = id;
        this.name = name;
        if(items != null){
            this.items = items;
            for(MenuItem item:items) {
                item.setMenu(this);
            }
        }
    }
}
