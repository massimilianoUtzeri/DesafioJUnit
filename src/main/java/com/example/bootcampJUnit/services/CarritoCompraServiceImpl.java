package com.example.bootcampJUnit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootcampJUnit.bbdd.BaseDatosServiceImpl;
import com.example.bootcampJUnit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI{
	
	@Autowired
	private BaseDatosServiceImpl baseDatos;

	List<Articulo> listaArticulos = new ArrayList<>();
	
	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		listaArticulos.add(articulo);
		
	}

	@Override
	public Integer getNumArticulo() {
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		Double total = 0D;
		for(Articulo articulo : listaArticulos) {
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentajeDescuento) {
		return precio-precio*porcentajeDescuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Double resultado = null;
		Articulo articulo = baseDatos.findArticuloById(idArticulo);
		
		if(articulo!=null) {
			resultado = calculadorDescuento(articulo.getPrecio(),porcentaje);
		}else {
			System.out.println("No se ha encontrado ningún articulo con ID: "+idArticulo);
		}
		
		return resultado;
	}

	@Override
	public Integer insertarArticuloById(Integer idArticulo,Articulo articulo) {
	return baseDatos.insertarArticuloById(idArticulo, articulo);
		
	}

}
