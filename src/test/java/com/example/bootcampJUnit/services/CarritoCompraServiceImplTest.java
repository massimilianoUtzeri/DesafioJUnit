package com.example.bootcampJUnit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.bootcampJUnit.bbdd.BaseDatosServiceImpl;
import com.example.bootcampJUnit.model.Articulo;
import com.example.bootcampJUnit.services.CarritoCompraServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	
	//@Autowired
	//private CarritoCompraServiceImpl carritoCompra;
	
	@InjectMocks
	private CarritoCompraServiceImpl service = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosServiceImpl bds;
	
	static List <Articulo> listaArticulos = new ArrayList<>();
	Articulo articulo = new Articulo("Jersey",10.00);
	Articulo articulo2 = new Articulo("Pantalón",20.55);
	Articulo articulo3 = new Articulo("Camiseta",22.95);
	Articulo articulo4 = new Articulo("Zapatos",88.15);

	/*@BeforeEach
	public void initCarritoCompra() {
		carritoCompra = new CarritoCompraServiceImpl();
	}
	*/
	
	@Test
	@Order(1)
	void testInsertarArticuloById() {
		Articulo articulo = new Articulo("Calcetines",5.0);
		Mockito.when(bds.insertarArticuloById(any(Integer.class),any(Articulo.class))).thenReturn(10);
		int id = service.insertarArticuloById(10, articulo);
		assertEquals(10,id);
		service.addArticulo(articulo);
		assertTrue(service.getArticulos().contains(articulo));
		verify(bds, atLeast(1)).insertarArticuloById(any(Integer.class),any(Articulo.class));
		
	}
	
	@Test
	@Order(2)
	void testAddArticulo() {
		
		listaArticulos.add(articulo);
		listaArticulos.add(articulo2);
		listaArticulos.add(articulo3);
		listaArticulos.add(articulo4);
		
		bds.insertarArticulo(articulo);
		Mockito.when(bds.insertarArticulo(any(Articulo.class))).thenReturn(true);
		assertTrue(service.getArticulos().contains(articulo));
		assertTrue(service.getArticulos().contains(articulo2));
		assertTrue(service.getArticulos().contains(articulo3));
		assertTrue(service.getArticulos().contains(articulo4));
		
		assertFalse(listaArticulos.isEmpty());
		verify(bds,atLeast(1)).insertarArticulo(any(Articulo.class));
	}
	
	@Test
	@Order(3)
	void testTotalPrice() {
		Double totalPrice = 0.0;
		for(Articulo articulo : listaArticulos) {
			totalPrice += articulo.getPrecio();
		}
		assertEquals(141.65,totalPrice);
	}
	
	@Test
	@Order(4)
	void testCalculadorDescuento() {
		double precioDescontado = 0;
		precioDescontado = service.calculadorDescuento(listaArticulos.get(0).getPrecio(), 12.00);
		listaArticulos.get(0).setPrecio(precioDescontado);
		assertEquals(precioDescontado,8.8);
		
	}
	
	@Test
	@Order(5)
	void testGetNumArticulo() {
		assertEquals(listaArticulos.size(),4);
	}
	
	@Test
	@Order(6)
	void testGetArticulos() {
		assertTrue(!listaArticulos.isEmpty());
	}
	
	@Test
	@Order(7)
	void testAplicarDescuento() {
		Articulo a = new Articulo("Chaqueta",10D);
		when(bds.findArticuloById(any(Integer.class))).thenReturn(a);
		Double resultado = service.aplicarDescuento(1, 12D);
		assertEquals(8.8D,resultado);
		verify(bds,atLeast(1)).findArticuloById(anyInt());
	}
	
	@Test
	@Order(8)
	void testLimpiarCesta() {
		listaArticulos.clear();
		assertTrue(listaArticulos.isEmpty());
		service.addArticulo(new Articulo("Camiseta",12.00));
		assertFalse(service.getArticulos().isEmpty());
		service.limpiarCesta();
		assertTrue(service.getArticulos().isEmpty());
		
	}
}