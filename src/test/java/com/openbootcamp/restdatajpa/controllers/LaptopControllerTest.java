package com.openbootcamp.restdatajpa.controllers;

import com.openbootcamp.restdatajpa.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar método findAll() de LaptopController")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertTrue(laptops.size() > 0);
        System.out.println("Laptops en la BD: " + laptops.size());
    }

    @DisplayName("Comprobar método findOneById() de Laptop Controller")
    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        // No existe la laptop  // Se pasa un id negativo // Existe la laptop
        if (response.getBody() == null){
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        } else if (response.getBody().getId() < 0){
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @DisplayName("Comprobar método create() de Laptop Controller")
    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                        "model": "MateBook 13s",
                        "brand": "Huawei",
                        "ram": 16,
                        "processor": "Intel i7",
                        "storage": 512,
                        "price": 39000.0
                    }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("MateBook 13s", result.getModel());
    }

    @DisplayName("Comprobar método update() de Laptop Controller")
    @Test
    void update() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        // No hay laptops en la BD
        if (response.getBody() == null){
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        // No existe la laptop
        if (response.getBody().getId() != 1 ) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        // Si existe la laptop
        if (response.getBody().getId() == 1){
            assertEquals(HttpStatus.OK, response.getStatusCode());
            response.getBody().setModel("MateBook test");
            assertNotEquals("MateBook 13s", response.getBody().getModel());
        }
    }

    @DisplayName("Comprobar método delete() de Laptop Controller")
    @Test
    void delete() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        // No existe la laptop  // Existe la laptop
        if (response.getBody() == null){
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @DisplayName("Comprobar método deleteAll() de Laptop Controller")
    @Test
    void deleteAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        if (response.getBody() == null){
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}