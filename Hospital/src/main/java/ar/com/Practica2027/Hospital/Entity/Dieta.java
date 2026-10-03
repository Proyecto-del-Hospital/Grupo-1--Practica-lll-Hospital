package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dieta {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 private String fechaInicio;
 private String fechaFin;
 private String descripcion;
 private String motivo;
 
 private Paciente paciente;
 
 private Sala sala;
	
 private Empleado empleado;

 private Estado estado;
 
}
