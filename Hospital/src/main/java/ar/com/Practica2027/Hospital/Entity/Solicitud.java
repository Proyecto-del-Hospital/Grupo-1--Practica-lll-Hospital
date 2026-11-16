package ar.com.Practica2027.Hospital.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fecha;
	private String motivo;

	@ManyToOne
	@JoinColumn(name = "id_Empleado")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	@OneToMany(mappedBy = "solicitud")
	private List<Pedido> pedidos = new ArrayList<>();

	@OneToMany(mappedBy = "solicitud")
	private List<Det_Solicitud> detallesSolicitudes = new ArrayList<>();

	
	public Solicitud() {
		super();
	}


	public Solicitud(int id, String fecha, String motivo, Empleado empleado, Estado estado, List<Pedido> pedidos,
			List<Det_Solicitud> detallesSolicitudes) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.empleado = empleado;
		this.estado = estado;
		this.pedidos = pedidos;
		this.detallesSolicitudes = detallesSolicitudes;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public List<Det_Solicitud> getDetallesSolicitudes() {
		return detallesSolicitudes;
	}


	public void setDetallesSolicitudes(List<Det_Solicitud> detallesSolicitudes) {
		this.detallesSolicitudes = detallesSolicitudes;
	}

	

}
