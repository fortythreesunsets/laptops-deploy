package com.openbootcamp.restdatajpa.controllers;

import com.openbootcamp.restdatajpa.entities.Laptop;
import com.openbootcamp.restdatajpa.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Operaciones CRUD

    /**
     * Buscar todas las laptops en la BD y devolverlas en un ArrayList
     * http://localhost:8080/api/laptops
     * @return List
     * */
    @GetMapping("/api/laptops")
    @ApiOperation("Recuperar todas las laptops de la BD en una lista")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    /**
     * Buscar una laptop por su id
     * http://localhost:8080/api/laptops/1
     * Request
     * Response
     * @param id
     * @return ResponseEntity
     * */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una laptop por Primary Key id de tipo Long")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
       Optional<Laptop> laptopOpt = laptopRepository.findById(id);

       if (laptopOpt.isPresent())
           return ResponseEntity.ok(laptopOpt.get());
        else
           return ResponseEntity.notFound().build();
    }

    /**
     * Crear una laptop en la BD
     * http://localhost:8080/api/laptops
     * @param laptop
     * @return ResponseEntity
     * */
    @PostMapping("/api/laptops")
    @ApiOperation("Agregar una laptop a la BD")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            log.warn("Trying to create a laptop with an existing Id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Actualizar datos de una laptop en la BD
     * http://localhost:8080/api/laptops
     * @param laptop
     * @return ResponseEntity
     * */
    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar/modificar atributos de laptop existente en BD")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("Can't be updated. The laptop does not exist");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("The laptop does not exist");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Borrar una laptop existente en la BD
     * http://localhost/api/laptops/1
     * @param id
     * @return ResponseEntity
     * */
    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Buscar en la BD una laptop existente por su Id y eliminarla")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if (!laptopRepository.existsById(id)){
            log.warn("Can't delete. The laptop does not exist");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Vaciar BD
     * http://localhost:8080/api/laptops
     * @return ResponseEntity
     */
    @DeleteMapping("/api/laptops")
    @ApiOperation("Borrar todas las laptops de la BD")
    public ResponseEntity<Laptop> deleteAll(){
        if (laptopRepository == null){
            log.warn("Database is empty");
            return ResponseEntity.badRequest().build();
        }else {
            log.info("REST request for deleting all laptops in database");
            laptopRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }
    }
}
