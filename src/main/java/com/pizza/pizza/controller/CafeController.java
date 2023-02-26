package com.pizza.pizza.controller;

import com.pizza.pizza.entity.Cafe;
import com.pizza.pizza.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CafeController {

    @Autowired
    CafeService cafeService;

    @GetMapping("/cafes")
    public ResponseEntity<Iterable<Cafe>> getAllCafes(){
        return  ResponseEntity.ok(cafeService.getAll());
    }

    @PostMapping("/cafe")
    public ResponseEntity<Cafe> addCafe(@RequestBody Cafe cafe){
        return ResponseEntity.ok(cafeService.addCafe(cafe));
    }

    @GetMapping("/cafe/full/{id}")
    public ResponseEntity<Cafe> getCafeByIdWithPizzas(@PathVariable Integer id){
        return ResponseEntity.ok(cafeService.getCafeByIdWithPizzas(id));
    }

    @PutMapping("/cafe/{id}")
    public ResponseEntity<Cafe> updateCafeById(@PathVariable Integer id, @RequestBody Cafe cafe){
        return ResponseEntity.ok(cafeService.updateById(id, cafe));
    }

    @DeleteMapping("/cafe/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        cafeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cafesByAddress")
    public ResponseEntity<List<Cafe>> getAllCafesByAddress(@RequestParam String address){
        return ResponseEntity.ok(cafeService.getCafesByAddress(address));
    }

}
