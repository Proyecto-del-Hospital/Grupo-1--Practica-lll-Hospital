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

    @OneToMany(mappedBy = "producto")
    private List<Det_Dieta> detallesDieta = new ArrayList<>();
    
	public Producto() {
		super();
	}

}
