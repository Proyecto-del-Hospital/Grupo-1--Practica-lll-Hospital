package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
    @ManyToOne
    @JoinColumn(name = "id_estado")
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;
	
	@OneToOne
	@JoinColumn(name = "id_contacto")
	private Contacto contacto;

	public Paciente() {
		super();
	}

	public Paciente(int id, String nombre, String apellido, String dni, String motivo, String fachenaci, Estado estado,
			Direccion direccion, Contacto contacto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.motivo = motivo;
		this.fachenaci = fachenaci;
		this.estado = estado;
		this.direccion = direccion;
		this.contacto = contacto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getFachenaci() {
		return fachenaci;
	}

	public void setFachenaci(String fachenaci) {
		this.fachenaci = fachenaci;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	
	
	
	
	
	
}
