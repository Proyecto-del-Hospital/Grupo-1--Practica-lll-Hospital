package ar.com.Practica2027.Hospital.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre_usuario;
	private String contrasena;
	
	@ManyToOne
	@JoinColumn (name = "id_rol")
	private Rol rol;
	
	@OneToOne (mappedBy = "usuario")
	private Empleado empleado;

	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre_usuario, String contrasena, Rol rol, Empleado empleado) {
		super();
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.contrasena = contrasena;
		this.rol = rol;
		this.empleado = empleado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}