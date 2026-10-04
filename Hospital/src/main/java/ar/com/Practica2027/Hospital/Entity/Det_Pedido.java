package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	
	private double cantidad;
	
	@ManyToOne
    @JoinColumn(name = "id_solicitud")
	private Solicitud solicitud;
	
	@ManyToOne
    @JoinColumn(name = "id_producto")
	private Producto producto;

	public Det_Pedido(int id, double cantidad, Solicitud solicitud, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.solicitud = solicitud;
		this.producto = producto;
	}

	public Det_Pedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
