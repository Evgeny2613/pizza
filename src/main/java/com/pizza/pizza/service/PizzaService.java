package com.pizza.pizza.service;

import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza addPizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Iterable<Pizza> getAllPizzasByCafe(Integer cafe_id){
        return pizzaRepository.findByCafeId(cafe_id);
    }

    public Pizza getPizzaById(Integer pizza_id){
        return pizzaRepository.findById(pizza_id).orElse(null);
    }

    public Pizza updatePizzaById(Integer pizza_id, Pizza pizzaRequest){
        Pizza findPizza = pizzaRepository.findById(pizza_id).orElse(null);
        if (findPizza != null && pizzaRequest != null){
            if (pizzaRequest.getCafeId() != null)
                findPizza.setCafeId(pizzaRequest.getCafeId());
            if (pizzaRequest.getKeyIngredients() != null)
                findPizza.setKeyIngredients(pizzaRequest.getKeyIngredients());
            if (pizzaRequest.getName() != null)
                findPizza.setName(pizzaRequest.getName());
            if (pizzaRequest.getPrice() != null)
                findPizza.setPrice(pizzaRequest.getPrice());
            if (pizzaRequest.getSize() != null)
                findPizza.setSize(pizzaRequest.getSize());
        }
        return pizzaRepository.save(pizzaRequest);
    }

    public void deleteById(Integer pizza_id){
        pizzaRepository.deleteById(pizza_id);
    }

    public Iterable<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    public List<Pizza> findAllPizzasByName(String name){
        return pizzaRepository.findAllByName(name);
    }

}
