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

		public Contacto() {
			super();
		}

		public Contacto(int id, String telefono, String correo, Paciente paciente, Empleado empleado,
				Proveedor proveedor) {
			super();
			this.id = id;
			this.telefono = telefono;
			this.correo = correo;
			this.paciente = paciente;
			this.empleado = empleado;
			this.proveedor = proveedor;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Empleado getEmpleado() {
			return empleado;
		}

		public void setEmpleado(Empleado empleado) {
			this.empleado = empleado;
		}

		public Proveedor getProveedor() {
			return proveedor;
		}

		public void setProveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}
	  	
	  	
}
