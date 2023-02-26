package com.pizza.pizza.controller;

import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping("/pizzasByCafe")
    public ResponseEntity<Iterable<Pizza>> getAllPizzasByCafeId(@RequestParam Integer id){
        return ResponseEntity.ok(pizzaService.getAllPizzasByCafe(id));
    }

    @PostMapping("/pizza")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza){
        return ResponseEntity.ok(pizzaService.addPizza(pizza));
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Integer id){
        return ResponseEntity.ok(pizzaService.getPizzaById(id));
    }

    @PutMapping("/pizza/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Integer id, @RequestBody Pizza pizza){
        return ResponseEntity.ok(pizzaService.updatePizzaById(id, pizza));
    }

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<HttpStatus> deletePizzaById(@PathVariable Integer id){
        pizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pizzas")
    public ResponseEntity<Iterable<Pizza>> getAllPizzas(){
        return ResponseEntity.ok(pizzaService.getAllPizzas());
    }

    @GetMapping("/pizzasByName")
    public ResponseEntity<List<Pizza>> getAllPizzasByName(@RequestParam String name){
        return ResponseEntity.ok(pizzaService.findAllPizzasByName(name));
    }
}
