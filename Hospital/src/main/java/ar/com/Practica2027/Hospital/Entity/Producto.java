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
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private Double stockactual;
	private Double stockminimo;
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
	
	@OneToMany(mappedBy = "producto")
    private List<Det_Factura> detallesFactura = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Det_Solicitud> detallesSolicitud = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Det_Pedido> detallesPedido = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Det_Consumo> detallesConsumo = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Det_Entrega> detallesEntrega = new ArrayList<>();

    
	public Producto() {
		super();
	}

	

	public Producto(int id, String nombre, Double stockactual, Double stockminimo, String motivo, Unidad unidad,
			SubTipo subtipo, Tipo tipo, Estado estado, List<Det_Factura> detallesFactura,
			List<Det_Solicitud> detallesSolicitud, List<Det_Pedido> detallesPedido, List<Det_Consumo> detallesConsumo,
			List<Det_Entrega> detallesEntrega) {
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
		this.detallesFactura = detallesFactura;
		this.detallesSolicitud = detallesSolicitud;
		this.detallesPedido = detallesPedido;
		this.detallesConsumo = detallesConsumo;
		this.detallesEntrega = detallesEntrega;
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

	public List<Det_Factura> getDetallesFactura() {
		return detallesFactura;
	}

	public void setDetallesFactura(List<Det_Factura> detallesFactura) {
		this.detallesFactura = detallesFactura;
	}

	public List<Det_Solicitud> getDetallesSolicitud() {
		return detallesSolicitud;
	}

	public void setDetallesSolicitud(List<Det_Solicitud> detallesSolicitud) {
		this.detallesSolicitud = detallesSolicitud;
	}

	public List<Det_Pedido> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(List<Det_Pedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	public List<Det_Consumo> getDetallesConsumo() {
		return detallesConsumo;
	}

	public void setDetallesConsumo(List<Det_Consumo> detallesConsumo) {
		this.detallesConsumo = detallesConsumo;
	}

	public List<Det_Entrega> getDetallesEntrega() {
		return detallesEntrega;
	}

	public void setDetallesEntrega(List<Det_Entrega> detallesEntrega) {
		this.detallesEntrega = detallesEntrega;
	}

	
	
	

}
