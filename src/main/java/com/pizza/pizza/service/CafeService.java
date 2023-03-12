package com.pizza.pizza.service;

import com.pizza.pizza.entity.Cafe;
import com.pizza.pizza.repository.CafeRepository;
import com.pizza.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeService {

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    PizzaRepository pizzaRepository;


    public Iterable<Cafe> getAll() {
        return cafeRepository.findAll();
    }

    public Cafe addCafe(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    public Cafe getCafeByIdWithPizzas(Integer cafe_id) {
        Cafe cafe = cafeRepository.findById(cafe_id).orElse(null);
        if (cafe != null) {
            cafe.setPizzaMenu(pizzaRepository.findByCafeId(cafe_id));
        }
        return cafe;
    }

    public Cafe updateById(Integer id, Cafe cafeRequest) {
        Cafe findCafe = cafeRepository.findById(id).orElse(null);
        if (cafeRequest != null && findCafe != null)
            if (cafeRequest.getAddress() != null)
                findCafe.setAddress(cafeRequest.getAddress());
            if (cafeRequest.getCity() != null)
                findCafe.setCity(cafeRequest.getCity());
            if (cafeRequest.getCloseAt() != null)
                findCafe.setCloseAt(cafeRequest.getCloseAt());
            if (cafeRequest.getEmail() != null)
                findCafe.setEmail(cafeRequest.getEmail());
            if (cafeRequest.getName() != null)
                findCafe.setName(cafeRequest.getName());
            if (cafeRequest.getOpenAt() != null)
                findCafe.setOpenAt(cafeRequest.getOpenAt());
            if (cafeRequest.getPhone() != null)
                findCafe.setPhone(cafeRequest.getPhone());

            return cafeRepository.save(findCafe);
    }

    public void deleteById(Integer cafe_id){
        cafeRepository.deleteById(cafe_id);
    }

    public List<Cafe> getCafesByAddress(String address){
        return cafeRepository.findAllByAddress(address);
    }

}
