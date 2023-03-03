package com.pizza.pizza.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pizza.pizza.controller.CafeController;
import com.pizza.pizza.service.CafeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.Target;
import java.sql.Time;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cafes")
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Should be present")
    @Length(min = 5, max = 15, message = "The lengths should be less than 5 and higher than 15")
    private String name;

    @NotNull(message = "Should be present")
    @Length(min = 3, max = 20,  message = "The lengths should be less than 3 and higher than 20")
    private String city;

    @NotNull(message = "Should be present")
    @Length(min = 10,  message = "The lengths should be higher than 10")
    private String address;

    @NotNull(message = "Should be present")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Should be present")
    @Length(min = 7, message = " message = The lengths should be higher than 7")
    private String phone;

    @Column(name = "open_at")
    @NotNull(message = "Should be present")
    private Time openAt;

    @Column(name = "close_at")
    @NotNull(message = "Should be present")
    private Time closeAt;

    @Column(name = "pizza_menu")
    @OneToMany(mappedBy = "cafeId")
    private List<Pizza> pizzaMenu;

    public Cafe(String name, String city, String address, String email, String phone, Time openAt, Time closeAt) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }
}
