package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Det_Entrega;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;

public interface IEntrega {
	String CrearEntrega (List<Det_Entrega> detalles, Empleado empleado, String fecha, String hora);
	List<Entrega> listarEntregas ();
	Entrega BuscarEntregaId (int id);
}
