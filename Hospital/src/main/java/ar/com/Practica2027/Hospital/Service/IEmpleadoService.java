package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Rol;

public interface IEmpleadoService {

	public String CrearEmpleado (Empleado empleado);
	public List<Rol>ListaRoles();
	public void CrearRol(Rol rol);
}
