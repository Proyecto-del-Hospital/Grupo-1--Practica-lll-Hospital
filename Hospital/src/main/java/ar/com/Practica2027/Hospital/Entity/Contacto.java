package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;


@Entity
public class Contacto {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	  	private String telefono;
	  	private String correo;	
	  	
	  	@OneToOne(mappedBy = "contacto")
	    private Paciente paciente;
	  	
	  	@OneToOne(mappedBy = "contacto")
	    private Empleado empleado;
	  	
	  	@OneToOne(mappedBy = "contacto")
	    private Proveedor proveedor;

}
