package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Det_Solicitud;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Solicitud;

public interface ISolicitud {
	String altaSolicitud(List<Det_Solicitud> detalles, Empleado empleados, String motivo );
	List<Solicitud> listasolictudes(); 
	Solicitud buscarporid(int id);
	List<Det_Solicitud> listadedetalles(int id);
	String CambiarEstadoaceptado(int id);
	String CambiarEstadorechazado(int id);
}
