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
    @JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "id_producto")
	private Producto producto;

	public Det_Pedido(int id, double cantidad, Pedido pedido, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.pedido = pedido;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	
}
