package com.pizza.pizza;

import com.pizza.pizza.entity.Cafe;
import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.repository.CafeRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.service.CafeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.ArgumentMatchers.any;

import java.sql.Time;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class CafeServiceTest {


    @InjectMocks
    private CafeService cafeService;

    @Mock
    private CafeRepository cafeRepository;

    @Mock
    private PizzaRepository pizzaRepository;


    @Test
    public void addCafe(){
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeRepository.save(cafe)).thenReturn(cafe);

        Cafe actual = cafeService.addCafe(cafe);

        assertThat(actual).usingRecursiveComparison().isEqualTo(cafe);
    }

    @Test
    public void getCafeById(){
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeRepository.findById(1)).thenReturn(Optional.of(cafe));

        Cafe actual = cafeService.getCafeByIdWithPizzas(1);

        assertThat(actual).isEqualTo(cafe);
    }

    @Test
    public void getAll(){
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeRepository.findAll()).thenReturn(List.of(cafe));

        List<Cafe> actual = (List<Cafe>) cafeService.getAll();

        assertThat(actual).isEqualTo(List.of(cafe));
    }

    @Test
    public void updateCafeById(){
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeRepository.findById(1)).thenReturn(Optional.of(cafe));
        when(cafeRepository.save(cafe)).thenReturn(cafe);


        Cafe actual = cafeService.updateById(1, cafe);

        assertThat(actual).isEqualTo(cafe);
    }

    @Test
    public void deleteCafeById(){
        doNothing().when(cafeRepository).deleteById(anyInt());

        cafeService.deleteById(anyInt());

        verify(cafeRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(cafeRepository);
    }

    @Test
    public void GetCafeByAddress(){
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeRepository.findAllByAddress("Berlin")).thenReturn(List.of(cafe));

        List<Cafe> listActual = cafeService.getCafesByAddress("Berlin");

        assertThat(listActual).isEqualTo(List.of(cafe));
    }


}
