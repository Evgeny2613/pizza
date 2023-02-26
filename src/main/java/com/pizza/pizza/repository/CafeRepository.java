package com.pizza.pizza.repository;

import com.pizza.pizza.entity.Cafe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends CrudRepository<Cafe, Integer> {

    List<Cafe> findAllByAddress(String address);
}
