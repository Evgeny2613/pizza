package com.pizza.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name = "pizza_size")
    private Character size;

    @Column(name = "key_ingredients")
    private String keyIngredients;

    private Double price;

    public Pizza(String name, Integer cafeId, Character size, String keyIngredients, Double price) {
        this.name = name;
        this.cafeId = cafeId;
        this.size = size;
        this.keyIngredients = keyIngredients;
        this.price = price;
    }
}
