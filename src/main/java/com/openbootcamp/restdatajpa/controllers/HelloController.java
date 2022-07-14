package com.openbootcamp.restdatajpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //Asociar una URL a este método
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola mundo desde clase HelloController";
    }
}
