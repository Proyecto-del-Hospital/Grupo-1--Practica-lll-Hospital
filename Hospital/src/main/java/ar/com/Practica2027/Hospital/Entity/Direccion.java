package ar.com.Practica2027.Hospital.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Direccion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String calle;
	private String Barrio;
	private String numer_calle;
	
	
    @OneToMany (mappedBy = "direccion")
    private List<Paciente> pacientes;
    
    @OneToMany (mappedBy = "direccion")
    private List<Proveedor> proveedores;
    
    @OneToMany(mappedBy = "direccion")
    private List<Empleado> empleados;

	public Direccion() {
		super();
	}

	public Direccion(int id, String calle, String barrio, String numer_calle, List<Paciente> pacientes,
			List<Proveedor> proveedores, List<Empleado> empleados) {
		super();
		this.id = id;
		this.calle = calle;
		Barrio = barrio;
		this.numer_calle = numer_calle;
		this.pacientes = pacientes;
		this.proveedores = proveedores;
		this.empleados = empleados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getBarrio() {
		return Barrio;
	}

	public void setBarrio(String barrio) {
		Barrio = barrio;
	}

	public String getNumer_calle() {
		return numer_calle;
	}

	public void setNumer_calle(String numer_calle) {
		this.numer_calle = numer_calle;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
}
