package com.pizza.pizza.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pizza.pizza.controller.CafeController;
import com.pizza.pizza.service.CafeService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String name;

    private String city;

    private String address;

    private  String email;

    private String phone;

    @Column(name = "open_at")
    private Time openAt;

    @Column(name = "close_at")
    private Time closeAt;

    @Column(name = "pizza_menu")
    @OneToMany(mappedBy = "cafeId")
    @JsonIgnore
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
