package com.pizza.pizza.repository;

import com.pizza.pizza.entity.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {
    List<Pizza> findByCafeId(Integer cafe_Id);

    List<Pizza> findAllByName(String name);
}
