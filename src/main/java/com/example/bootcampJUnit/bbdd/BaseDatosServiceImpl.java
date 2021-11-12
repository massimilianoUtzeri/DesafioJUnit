package com.example.bootcampJUnit.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.bootcampJUnit.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI {
	
	Map<Integer,Articulo> storage;

	@Override
	public void initBD() {
		
		storage = new HashMap<>();
		storage.put(1,new Articulo("Camiseta",18.95));
		storage.put(2,new Articulo("Jersey",21.95));
		storage.put(3,new Articulo("Pantalon",15.95));
		storage.put(4,new Articulo("Zapatos",45.95));
		storage.put(5,new Articulo("Sudadera",12.95));
		storage.put(6,new Articulo("Chaqueta",38.95));
		storage.put(7,new Articulo("Chandal",40.95));
		storage.put(8,new Articulo("Cinturon",10.95));
	}

	@Override
	public Articulo findArticuloById(Integer identificador) {
		
		System.out.println("Buscando el articulo con ID: "+identificador);
		return storage.get(identificador);
	}

	@Override
	public boolean insertarArticulo(Articulo articulo) {
		
		System.out.println("Insertando el articulo con nombre"+articulo.getNombre());
		storage.put(storage.size()+1, articulo);
		return true;
	}

	@Override
	public Integer lastIndex() {
	
		return storage.size();
	}

	@Override
	public Integer insertarArticuloById(Integer id, Articulo articulo) {
		storage.put(id, articulo);
		return id;
	}

}
