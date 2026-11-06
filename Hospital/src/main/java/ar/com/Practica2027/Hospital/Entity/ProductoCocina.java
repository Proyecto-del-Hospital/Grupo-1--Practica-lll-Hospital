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
public class ProductoCocina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private Double stockactual;
	private Double stockminimo;
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name = "id_unidad")
	private Unidad unidad;

	@ManyToOne
	@JoinColumn(name = "id_subtipo")
	private SubTipo subtipo;

	@ManyToOne
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;
	
	@OneToMany(mappedBy = "productococina")
	private List<Det_Consumo> detallesConsumo = new ArrayList<>();

	public ProductoCocina() {
		super();
	}

	public ProductoCocina(int id, String nombre, Double stockactual, Double stockminimo, String motivo, Unidad unidad,
			SubTipo subtipo, Tipo tipo, Estado estado, List<Det_Consumo> detallesConsumo) {
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
		this.detallesConsumo = detallesConsumo;
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

	public Double getStockactual() {
		return stockactual;
	}

	public void setStockactual(Double stockactual) {
		this.stockactual = stockactual;
	}

	public Double getStockminimo() {
		return stockminimo;
	}

	public void setStockminimo(Double stockminimo) {
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

	public List<Det_Consumo> getDetallesConsumo() {
		return detallesConsumo;
	}

	public void setDetallesConsumo(List<Det_Consumo> detallesConsumo) {
		this.detallesConsumo = detallesConsumo;
	}
	
	
}
