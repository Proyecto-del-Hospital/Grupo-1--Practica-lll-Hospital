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
 private Paciente paciente;
 private Empleado empleado;
 private String fechaInicio;
 private String fechaFin;
 private String descripcion;
 private Estado estado;
 private String motivo;

}
