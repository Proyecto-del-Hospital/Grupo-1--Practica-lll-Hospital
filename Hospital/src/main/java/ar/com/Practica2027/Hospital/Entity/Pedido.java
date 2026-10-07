package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Pedido {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fecha;
	private String motivo;

	
	@OneToOne(mappedBy = "pedido")
	private Factura factura;

	@OneToOne
	@JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;
	
	@OneToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;
	
	@ManyToOne
    @JoinColumn(name = "id_estado")
	private Estado estado;

	public Pedido(int id, String fecha, String motivo, Factura factura, Solicitud solicitud, Empleado empleado,
			Estado estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.factura = factura;
		this.solicitud = solicitud;
		this.empleado = empleado;
		this.estado = estado;
	}

	public Pedido() {
		super();
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
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
	
	
}
