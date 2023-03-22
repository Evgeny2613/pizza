package com.pizza.pizza;

import com.pizza.pizza.entity.Pizza;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Handler;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PizzaSecurityTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void createPizzaAdmin() throws Exception {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);

        ResponseEntity<Pizza> actual = template.withBasicAuth("admin", "admin")
                .postForEntity("/pizza", pizza, Pizza.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void createPizzaUser() throws Exception {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);

        ResponseEntity<Pizza> actual = template.withBasicAuth("user", "user")
                .postForEntity("/pizza", pizza, Pizza.class);

        assertEquals(HttpStatus.FORBIDDEN, actual.getStatusCode());
    }

    @Test
    public void createPizzaNoAuth() throws Exception {
        Pizza pizza = new Pizza("Margarita", 1, 'M', "Cheese, Tomato, TEST, TEST, TEST", 7.0);

        ResponseEntity<Pizza> actual = template
                .postForEntity("/pizza", pizza, Pizza.class);

        assertEquals(HttpStatus.UNAUTHORIZED, actual.getStatusCode());
    }
}
