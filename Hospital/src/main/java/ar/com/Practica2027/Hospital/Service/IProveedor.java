package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Proveedor;

public interface IProveedor {

	void CrearProveedor(Proveedor proveedor);
	List<Proveedor> ListarProveedores();
	
	
}
