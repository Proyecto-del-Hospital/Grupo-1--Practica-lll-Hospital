package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Producto;

public interface IProducto {
	String CrearProducto (Producto producto);
	List<Producto> ListarProductos ();
	Producto buscarProducto (String nombre);
	String ModificarProducto (Producto producto);
	Producto buscarProductoPorId (int id);
	String CambiarEstado (Producto producto);
}
