package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double cantidad;
	private double preciounitario;
	private double subtotal;

	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura factura;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Det_Factura(int id, int cantidad, float preciounitario, float subtotal, Factura factura,
			Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.preciounitario = preciounitario;
		this.subtotal = subtotal;
		this.factura = factura;
		this.producto = producto;
	}

	public Det_Factura() {
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

	public double getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
