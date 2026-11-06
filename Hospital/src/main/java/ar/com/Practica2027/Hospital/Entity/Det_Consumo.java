package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Consumo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private double cantidad;
		
	@ManyToOne
	@JoinColumn(name = "id_consumo")
	private Consumo consumo;
	
	@ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
	
	@ManyToOne
    @JoinColumn(name = "id_productococina")
    private ProductoCocina productococina;
	
	@ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;

	public Det_Consumo() {
		super();
	}

	public Det_Consumo(int id, double cantidad, Consumo consumo, Empleado empleado, ProductoCocina productococina,
			Turno turno) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.consumo = consumo;
		this.empleado = empleado;
		this.productococina = productococina;
		this.turno = turno;
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

	public Consumo getConsumo() {
		return consumo;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ProductoCocina getProductococina() {
		return productococina;
	}

	public void setProductococina(ProductoCocina productococina) {
		this.productococina = productococina;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	
}