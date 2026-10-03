package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Empleado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String motivo;
	
	@OneToOne
	@JoinColumn (name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne 
	@JoinColumn (name = "id_direccion")
	private Direccion direccion;
	
	@OneToOne
	@JoinColumn (name = "id_contacto")
	private Contacto contacto;
	
	@ManyToOne
	@JoinColumn (name = "id_estado")
	private Estado estado;

	public Empleado() {
		super();
	}

	public Empleado(int id, String nombre, String apellido, String motivo, Usuario usuario, Direccion direccion,
			Contacto contacto, Estado estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.motivo = motivo;
		this.usuario = usuario;
		this.direccion = direccion;
		this.contacto = contacto;
		this.estado = estado;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

	
}
