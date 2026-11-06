package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.ProductoCocina;

public interface IProductoCocina {
	String crearProductoCocina (ProductoCocina productoC);
	List<ProductoCocina> ListarProductosC ();
	ProductoCocina buscarProductoC (String nombre);
	String modificarProductoC (ProductoCocina productoC);
	ProductoCocina buscarProductocPorId (int id);
	String CambiarEstado (ProductoCocina productoC);
	List<ProductoCocina> BuscarEstado (String nombre);
}
