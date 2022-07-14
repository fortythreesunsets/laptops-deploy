package com.openbootcamp.restdatajpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/home")
    public String home() {
        return """
                <!doctype html>
                <html lang="en">
                    <head>
                       <meta charset="utf-8">
                       <meta name="viewport" content="width=device-width, initial-scale=1">
                       <title>Deploy Inventario Laptops</title>
                       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
                     </head>
                     <body>
                       <h3>user: 123456</h3>
                       <h3>admin: password</h3>
                       <a class="btn btn-primary" href="http://localhost:8080/api/laptops">Ver laptops</a>
                       <a class="btn btn-primary" href="http://localhost:8080/api/laptops/1">Ver laptop 1</a>
                       <a class="btn btn-primary" href="http://localhost:8080/api/laptops/2">Ver laptop 2</a>
                       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
                     </body>
                </html>
                """;
    }
}
