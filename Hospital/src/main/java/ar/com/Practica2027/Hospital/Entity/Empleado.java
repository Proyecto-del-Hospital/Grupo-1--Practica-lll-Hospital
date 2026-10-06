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
import jakarta.persistence.OneToOne;

@Entity
public class Empleado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String DNI;
	private String motivo;
	private String fecha_nac;
	
	@OneToOne
	@JoinColumn (name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne 
	@JoinColumn (name = "id_direccion")
	private Direccion direccion;
	
	@OneToOne
	@JoinColumn (name = "id_contacto")
	private Contacto contacto;
	
	@ManyToOne
	@JoinColumn (name = "id_estado")
	private Estado estado;
	
	@OneToMany(mappedBy = "empleado")
	private List<Det_Sector> sectores =  new ArrayList<>();
	
	@OneToMany(mappedBy = "empleado")
	private List<Solicitud> solicitudesregistradas =  new ArrayList<>();
	
	@OneToMany(mappedBy = "empleado")
	private List<Entrega> entregasregistradas =  new ArrayList<>();
	
	@OneToMany(mappedBy = "empleado")
    private List<Pedido> Pedidosregistrados =  new ArrayList<>();
	
	@OneToMany(mappedBy = "empleado")
	private List<Det_Consumo> consumosregistrados =  new ArrayList<>();
	 
	@OneToMany(mappedBy = "empleado")
    private List<DietaMovimiento> movimientosregistrados =  new ArrayList<>();
	
	
	public Empleado() {
		super();
	}


	public Empleado(int id, String nombre, String apellido, String dNI, String motivo, String fecha_nac,
			Usuario usuario, Direccion direccion, Contacto contacto, Estado estado, List<Det_Sector> sectores,
			List<Solicitud> solicitudesregistradas, List<Entrega> entregasregistradas, List<Pedido> pedidosregistrados,
			List<Det_Consumo> consumosregistrados, List<DietaMovimiento> movimientosregistrados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.motivo = motivo;
		this.fecha_nac = fecha_nac;
		this.usuario = usuario;
		this.direccion = direccion;
		this.contacto = contacto;
		this.estado = estado;
		this.sectores = sectores;
		this.solicitudesregistradas = solicitudesregistradas;
		this.entregasregistradas = entregasregistradas;
		Pedidosregistrados = pedidosregistrados;
		this.consumosregistrados = consumosregistrados;
		this.movimientosregistrados = movimientosregistrados;
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


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getFecha_nac() {
		return fecha_nac;
	}


	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	public Contacto getContacto() {
		return contacto;
	}


	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public List<Det_Sector> getSectores() {
		return sectores;
	}


	public void setSectores(List<Det_Sector> sectores) {
		this.sectores = sectores;
	}


	public List<Solicitud> getSolicitudesregistradas() {
		return solicitudesregistradas;
	}


	public void setSolicitudesregistradas(List<Solicitud> solicitudesregistradas) {
		this.solicitudesregistradas = solicitudesregistradas;
	}


	public List<Entrega> getEntregasregistradas() {
		return entregasregistradas;
	}


	public void setEntregasregistradas(List<Entrega> entregasregistradas) {
		this.entregasregistradas = entregasregistradas;
	}


	public List<Pedido> getPedidosregistrados() {
		return Pedidosregistrados;
	}


	public void setPedidosregistrados(List<Pedido> pedidosregistrados) {
		Pedidosregistrados = pedidosregistrados;
	}


	public List<Det_Consumo> getConsumosregistrados() {
		return consumosregistrados;
	}


	public void setConsumosregistrados(List<Det_Consumo> consumosregistrados) {
		this.consumosregistrados = consumosregistrados;
	}


	public List<DietaMovimiento> getMovimientosregistrados() {
		return movimientosregistrados;
	}


	public void setMovimientosregistrados(List<DietaMovimiento> movimientosregistrados) {
		this.movimientosregistrados = movimientosregistrados;
	}
}
