package com.example.bootcampJUnit.bbdd;

import com.example.bootcampJUnit.model.Articulo;

public interface BaseDatosServiceI {
	
	public void initBD();
	
	public Articulo findArticuloById(Integer identificador);
	
	public boolean insertarArticulo(Articulo articulo);
	
	public Integer lastIndex();
	
	public Integer insertarArticuloById(Integer id, Articulo articulo);

}
