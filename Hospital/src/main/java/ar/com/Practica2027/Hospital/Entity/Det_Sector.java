package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Det_Sector {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Empleado empleado;
	private Sector sector;
}
