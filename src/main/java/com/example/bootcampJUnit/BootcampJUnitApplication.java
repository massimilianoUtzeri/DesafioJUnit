package com.example.bootcampJUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bootcampJUnit.bbdd.BaseDatosServiceI;
import com.example.bootcampJUnit.bbdd.BaseDatosServiceImpl;
import com.example.bootcampJUnit.model.Articulo;

@SpringBootApplication
public class BootcampJUnitApplication implements CommandLineRunner{
	
	@Autowired
	BaseDatosServiceI baseDatosService;

	public static void main(String[] args) {
		SpringApplication.run(BootcampJUnitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		baseDatosService.initBD();
		Articulo articulo = new Articulo("Calcetines",5.95);
		baseDatosService.insertarArticulo(articulo);
		baseDatosService.findArticuloById(9);
		baseDatosService.findArticuloById(baseDatosService.lastIndex());
		
	}

}
