package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Paciente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private String motivo;
	private String fachenaci;
	

    @OneToMany
    @JoinColumn(name = "id_estado")
	private Estado estado;
	
	 @OneToMany
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;
	
	@OneToOne
	@JoinColumn(name = "id_contacto")
	private Contacto contacto;
	
	
	
	
	
}
