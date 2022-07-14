package com.openbootcamp.restdatajpa.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@ApiModel("Entidad/Clase Laptop para representar una laptop en la BD")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Primary Key autoincremental tipo Long")
    private Long id;
    private String model;
    private String brand;
    @ApiModelProperty("Memoria RAM en GB")
    private int ram;
    private String processor;
    @ApiModelProperty("Capacidad de disco duro en GB")
    private int storage;
    @ApiModelProperty("Precio en pesos mexicanos")
    private Double price;

    //Constructores
    public Laptop(){}

    public Laptop(Long id, String model, String brand, int ram, String processor, int storage, Double price) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.ram = ram;
        this.processor = processor;
        this.storage = storage;
        this.price = price;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
