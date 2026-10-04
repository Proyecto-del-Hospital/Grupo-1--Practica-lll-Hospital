package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Entrega {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private double cantidad;
	
	@ManyToOne
    @JoinColumn(name = "id_entrega")
    private Entrega entrega;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Det_Entrega(int id, double cantidad, Entrega entrega, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.entrega = entrega;
		this.producto = producto;
	}

	public Det_Entrega() {
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

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
}
