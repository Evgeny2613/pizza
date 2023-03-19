package com.pizza.pizza;

import com.google.gson.Gson;
import com.pizza.pizza.controller.CafeController;
import com.pizza.pizza.entity.Cafe;
import com.pizza.pizza.repository.CafeRepository;
import com.pizza.pizza.service.CafeService;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CafeController.class)
@RunWith(SpringRunner.class)
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class CafeControllerTest {

    @MockBean
    private CafeService cafeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shouldCreateMockMVC() {
        assertNotNull(mockMvc);
    }

    @Test
    public void createValidCafe() throws Exception {
        mockMvc.perform(
                post("/cafe")
                        .content("{\"name\": \"Valid Cafe Test\", \"city\": \"Berlin\", \"address\": \"Potsdamer Str. 148\", \"email\": \"test@gmail.com\", \"phone\": \"+4912345678\", \"openAt\": \"10:00:00\", \"closeAt\": \"18:00:00\"}")
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    public void getAllCafes() throws Exception {
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        Cafe cafe2 = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));
        List<Cafe> cafes = new ArrayList<>(List.of(cafe, cafe2));

        when(cafeService.getAll()).thenReturn(cafes);

        mockMvc.perform(get("/cafes")
                .contentType("application/json")
        ).andExpect(status().isOk());

        verify(cafeService, Mockito.times(1)).getAll();

    }

    @Test
    public void getCafeById() throws Exception {
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));;


        when(cafeService.getCafeByIdWithPizzas(1)).thenReturn(cafe);

        mockMvc.perform(
                get("/cafe/full/1")
                        .contentType("application/json")
        ).andExpect(status().isOk());

        verify(cafeService, Mockito.times(1)).getCafeByIdWithPizzas(1);
    }

    @Test
    public void updateCafeById() throws Exception{
        mockMvc.perform(
                put("/cafe/2")
                        .contentType("application/json")
                        .content("{\"name\": \"New Cafe Update\",  \"city\": \"Berlin\", \"address\": \"Potsdamer Str. 148\", \"email\": \"test@gmail.com\", \"phone\": \"+4912345678\", \"openAt\": \"10:00:00\", \"closeAt\": \"18:00:00\"}")
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteCafeById() throws Exception{
        mockMvc.perform(
                delete("/cafe/1")
        ).andExpect(status().isNoContent());

        verify(cafeService, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void searchByCafeAddress() throws Exception{
        mockMvc.perform(
                get("/cafes")
                        .param("address", "Berlin")
        ).andExpect(status().isOk());
    }


}
