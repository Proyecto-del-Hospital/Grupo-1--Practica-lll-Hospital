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
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private double cantidad;
	private double precio_unitario;
	private double subtotal;
	
	@ManyToOne
    @JoinColumn(name = "id_factura")
	private Factura factura;
	
	@ManyToOne
    @JoinColumn(name = "id_producto")
	private Producto producto;

	public Det_Factura(int id, int cantidad, float precio_unitario, float subtotal, Factura factura,
			Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
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

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
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
