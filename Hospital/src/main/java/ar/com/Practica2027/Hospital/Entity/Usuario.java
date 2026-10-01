package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre_usuario;
	private String contraseña;
	private Rol rol;
	
}
