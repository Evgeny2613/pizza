package com.pizza.pizza;

import com.google.gson.Gson;
import com.pizza.pizza.controller.PizzaController;
import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.service.PizzaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PizzaController.class)
@RunWith(SpringRunner.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllPizzasByCafe() throws Exception{
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato", 7.0);
        when(pizzaService.getAllPizzas()).thenReturn(List.of(pizza));

        mockMvc.perform(
                get("/pizzas")
                        .param("cafe_id", "1")
        ).andExpect(status().isOk());

        verify(pizzaService, Mockito.times(1)).getAllPizzas();
    }

    @Test
    public void addPizza() throws Exception{
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        Gson gson = new Gson();
        String pizzaGson = gson.toJson(pizza);

        mockMvc.perform(
                post("/pizza")
                        .content(pizzaGson)
                        .contentType("application/json")
        ).andExpect(status().isOk());
}

    @Test
    public void updatePizzaById() throws Exception{
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        Gson gson = new Gson();
        String pizzaGson = gson.toJson(pizza);

        mockMvc.perform(
                put("/pizza/1")
                        .content(pizzaGson)
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    public void DeletePizzaById() throws Exception{
        mockMvc.perform(
                delete("/pizza/1")
        ).andExpect(status().isNoContent());

        verify(pizzaService, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void getAllPizzas() throws Exception{
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);
        when(pizzaService.getAllPizzas()).thenReturn(List.of(pizza));

        mockMvc.perform(
                get("/pizzas")
        ).andExpect(status().isOk());

        verify(pizzaService, Mockito.times(1)).getAllPizzas();
    }

    @Test
    public void getPizzaByName() throws Exception {
        mockMvc.perform(
                get("/pizzas")
                        .param("name", "Margarita")
        ).andExpect(status().isOk());
    }

}
