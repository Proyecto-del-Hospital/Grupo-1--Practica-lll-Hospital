package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Solicitud {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Empleado empleado;
	private String fecha;
	private Estado estado;
	private String motivo;
	
}
