package com.openbootcamp.restdatajpa;

import com.openbootcamp.restdatajpa.repositories.LaptopRepository;
import com.openbootcamp.restdatajpa.entities.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@SpringBootApplication
public class RestDataJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(RestDataJpaApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// Crear laptop
		Laptop laptop1 = new Laptop(null, "Ideapad Y700", "Lenovo", 8, "Intel Core i5", 1024, 17000.00);
		Laptop laptop2 = new Laptop(null, "GS75 Stealth", "MSI", 16, "Intel Core i7", 512, 39000.00);

		System.out.println("Número de laptops en BD: " + repository.findAll().size());

		// Almacenar laptop
		repository.save(laptop1);
		repository.save(laptop2);

		// Recuperar todas las laptops
		System.out.println("Número de laptops en BD: " + repository.findAll().size());
	}

}
