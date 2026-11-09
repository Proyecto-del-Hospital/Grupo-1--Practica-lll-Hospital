package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Det_Solicitud;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Solicitud;

public interface ISolicitud {
	String altaSolicitud(List<Det_Solicitud> detalles, Empleado empleados, String motivo );
	List<Solicitud> listasolictudes(); 
	

}
