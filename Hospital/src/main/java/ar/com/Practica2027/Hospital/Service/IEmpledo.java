package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Sector;

public interface IEmpledo {

    String CrearEmpleado (Empleado empleado, List<Integer>sectoresids );
	List<Sector> listarSector();
	String guardarsector(Sector sector);
	List<Empleado> ListarEmpleados(); 
	List<Empleado> buscarEmpleado(String DNI);
	Empleado buscarporid(int id);
	String modificarempleado(Empleado empleado);
	String CambiarEstadoempleado(Empleado empleado);
}
