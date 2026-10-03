package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private int stockactual;
	private int stockminimo;
	private String motivo;
	
	@ManyToOne
	@JoinColumn (name = "id_unidad")
	private Unidad unidad;
	
	@ManyToOne
	@JoinColumn (name = "id_subtipo")
	private SubTipo subtipo;
	
	@ManyToOne
	@JoinColumn (name = "id_tipo")
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn (name = "id_estado")
	private Estado estado;
	
	public Producto() {
		super();
	}

	public Producto(int id, String nombre, int stockactual, int stockminimo, String motivo, Unidad unidad,
			SubTipo subtipo, Tipo tipo, Estado estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stockactual = stockactual;
		this.stockminimo = stockminimo;
		this.motivo = motivo;
		this.unidad = unidad;
		this.subtipo = subtipo;
		this.tipo = tipo;
		this.estado = estado;
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

	public int getStockactual() {
		return stockactual;
	}

	public void setStockactual(int stockactual) {
		this.stockactual = stockactual;
	}

	public int getStockminimo() {
		return stockminimo;
	}

	public void setStockminimo(int stockminimo) {
		this.stockminimo = stockminimo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public SubTipo getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(SubTipo subtipo) {
		this.subtipo = subtipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}
