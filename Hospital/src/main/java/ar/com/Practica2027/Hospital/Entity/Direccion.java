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
	
	 // Relación: una dirección puede estar asociada a varios Paciente
    @OneToMany(mappedBy = "direccion")
    private List<Paciente> paciente;
    
    // Relación: una dirección puede estar asociada a varios Proveedor
    @OneToMany(mappedBy = "direccion")
    private List<Proveedor> proveedor;
    
    // Relación: una dirección puede estar asociada a varios Empleado
    @OneToMany(mappedBy = "direccion")
    private List<Empleado> empleado;

	public Direccion() {
		super();
	}

	public Direccion(int id, String calle, String barrio, String numer_calle, List<Paciente> paciente,
			List<Proveedor> proveedor, List<Empleado> empleado) {
		super();
		this.id = id;
		this.calle = calle;
		Barrio = barrio;
		this.numer_calle = numer_calle;
		this.paciente = paciente;
		this.proveedor = proveedor;
		this.empleado = empleado;
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

	public List<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(List<Paciente> paciente) {
		this.paciente = paciente;
	}

	public List<Proveedor> getProveedor() {
		return proveedor;
	}

	public void setProveedor(List<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}
    
    
}
