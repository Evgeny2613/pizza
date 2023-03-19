package com.pizza.pizza;

import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.service.PizzaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class PizzaServiceTest {

    @InjectMocks
    private PizzaService pizzaService;

    @Mock
    private PizzaRepository pizzaRepository;

    @Test
    public void getPizzaByCafeId() {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.findByCafeId(1)).thenReturn(List.of(pizza));

        List<Pizza> actual = (List<Pizza>) pizzaService.getAllPizzasByCafe(1);

        assertThat(actual).isEqualTo(List.of(pizza));
    }

    @Test
    public void addPizza() {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.save(pizza)).thenReturn(pizza);

        Pizza actual = pizzaService.addPizza(pizza);

        assertThat(actual).isEqualTo(pizza);
    }

    @Test
    public void getPizzaById() {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.findById(1)).thenReturn(Optional.of(pizza));

        Pizza actual = pizzaService.getPizzaById(1);

        assertThat(actual).isEqualTo(pizza);
    }

    @Test
    public void updatePizzaById() {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.save(pizza)).thenReturn(pizza);

        Pizza actual = pizzaService.updatePizzaById(1, pizza);

        assertThat(actual).isEqualTo(pizza);
    }

    @Test
    public void deleteById(){
        doNothing().when(pizzaRepository).deleteById(1);

        pizzaRepository.deleteById(1);

        verify(pizzaRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(pizzaRepository);
    }

    @Test
    public void getAll(){
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.findAll()).thenReturn(List.of(pizza));

        List<Pizza> actual = (List<Pizza>) pizzaService.getAllPizzas();

        assertThat(actual).isEqualTo(List.of(pizza));
    }

    @Test
    public void getPizzaByName(){
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaRepository.findAllByName("Margarita")).thenReturn(List.of(pizza));

        List<Pizza> actual = pizzaService.findAllPizzasByName("Margarita");

        assertThat(actual).isEqualTo(List.of(pizza));
    }
}
