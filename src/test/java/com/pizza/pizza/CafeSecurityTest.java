package com.pizza.pizza;

import com.pizza.pizza.entity.Cafe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Time;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CafeSecurityTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void createCafeAdmin() throws Exception {
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));

        ResponseEntity<Cafe> actual = template.withBasicAuth("admin", "admin")
                .postForEntity("/cafe", cafe, Cafe.class);


        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void createCafeUser() throws  Exception{
    Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));

    ResponseEntity<Cafe> actual = template.withBasicAuth("user", "user")
            .postForEntity("/cafe", cafe, Cafe.class);


    assertEquals(HttpStatus.FORBIDDEN, actual.getStatusCode());
    }

    @Test
    public void createCafeNoAuth() throws  Exception{
        Cafe cafe = new Cafe("TEST TEST", "Berlin", "TEST ADDRESS CITY TOWN", "pizza@gmail.com", "+491414131313", Time.valueOf("10:00:00"), Time.valueOf("18:00:00"));

        ResponseEntity<Cafe> actual = template
                .postForEntity("/cafe", cafe, Cafe.class);


        assertEquals(HttpStatus.UNAUTHORIZED, actual.getStatusCode());
    }


}
