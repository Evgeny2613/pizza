package com.pizza.pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Should be present")
    @Length(min = 2, max = 15, message = "The lengths should be less than 2 and higher than 15")
    private String name;

    @NotNull(message = "Should be present")
    @Column(name = "cafe_id")
    @Min(value = 1, message = "Should equal 1 or be higher")
    private Integer cafeId;

    @NotNull(message = "Should be present")
    @Column(name = "pizza_size")
    private Character size;

    @NotNull(message = "Should be present")
    @Length(min = 10, message = "The lengths should be higher than 10")
    @Column(name = "key_ingredients")
    private String keyIngredients;

    @NotNull(message = "Should be present")
    @Min(value = 1, message = "Should be higher than 1")
    @Max(value = 30, message = "Should be less that 30")
    private Double price;

    public Pizza(String name, Integer cafeId, Character size, String keyIngredients, Double price) {
        this.name = name;
        this.cafeId = cafeId;
        this.size = size;
        this.keyIngredients = keyIngredients;
        this.price = price;
    }
}
