package ar.com.Practica2027.Hospital.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToMany (mappedBy = "estado")
	private List<Empleado> empleados;
	
	@OneToMany (mappedBy = "estado")
	private List<Paciente> pacientes;
	
	@OneToMany (mappedBy = "estado")
	private List<Proveedor> proveedores;
	
	@OneToMany (mappedBy = "estado")
	private List<Pedido> pedidos;
	
	@OneToMany (mappedBy = "estado")
	private List<Solicitud> solicitudes;
	
	@OneToMany (mappedBy = "estado")
	private List<Producto> productos;
	
	@OneToMany (mappedBy = "estado")
	private List<Dieta> dietas;

	public Estado() {
		super();
	}

	public Estado(int id, String nombre, List<Empleado> empleados, List<Paciente> pacientes,
			List<Proveedor> proveedores, List<Pedido> pedidos, List<Solicitud> solicitudes, List<Producto> productos,
			List<Dieta> dietas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.empleados = empleados;
		this.pacientes = pacientes;
		this.proveedores = proveedores;
		this.pedidos = pedidos;
		this.solicitudes = solicitudes;
		this.productos = productos;
		this.dietas = dietas;
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

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}
	
	
}
