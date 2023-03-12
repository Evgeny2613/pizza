package com.pizza.pizza;

import com.pizza.pizza.controller.CafeController;
import com.pizza.pizza.entity.Cafe;
import com.pizza.pizza.repository.CafeRepository;
import com.pizza.pizza.service.CafeService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CafeController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PizzaControllerTest {

    @MockBean
    private CafeService cafeService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CafeRepository cafeRepository;


    @Test
   public void shouldCreateMockMVC(){
        assertNotNull(mockMvc);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void createValidCafe() throws Exception{
        Cafe cafe = new Cafe("TEST Name", "Berlin", "Address TEST City Country", "test@gmail.com", "+4929334855", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        when(cafeService.addCafe(cafe)).thenReturn(cafe);


        mockMvc.perform(
                post("/cafe")
//                        .content("{\"name\": \"Valid Cafe Test\", \"city\": \"Berlin\", \"address\": \"Potsdamer Str. 148\", \"email\": \"test@gmail.com\", \"phone\": \"+4912345678\", \"openAt\": \"10:00:00\", \"closeAt\": \"18:00:00\"}")
                        .content(String.valueOf(cafe))
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }
}
