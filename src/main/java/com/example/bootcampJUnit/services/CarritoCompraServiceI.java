package com.example.bootcampJUnit.services;

import java.util.List;

import com.example.bootcampJUnit.model.Articulo;

public interface CarritoCompraServiceI {
	
	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public Integer getNumArticulo();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calculadorDescuento(Double precio,Double porcentajeDescuento);
	
	public Double aplicarDescuento(Integer idArticulo,Double porcentaje);
	
	public Integer insertarArticuloById(Integer idArticulo,Articulo articulo);
	
	

}
