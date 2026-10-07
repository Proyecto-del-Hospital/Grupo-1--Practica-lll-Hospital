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
public class Factura {
@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;


private String num_factura;
private String fecha_emision;
private double total;

@ManyToOne
@JoinColumn(name ="id_Proveedor")
private Proveedor proveedor;

//Una Factura se genera a partir de UN único Pedido
@OneToOne
@JoinColumn(name = "id_pedido")
private Pedido pedido;

@OneToMany(mappedBy = "factura")
private List<Det_Factura> detallesFactura = new ArrayList<>();

public Factura(int id, String num_factura, String fecha_emision, double total, Proveedor proveedor, Pedido pedido,
		List<Det_Factura> detallesFactura) {
	super();
	this.id = id;
	this.num_factura = num_factura;
	this.fecha_emision = fecha_emision;
	this.total = total;
	this.proveedor = proveedor;
	this.pedido = pedido;
	this.detallesFactura = detallesFactura;
}

public Factura() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNum_factura() {
	return num_factura;
}

public void setNum_factura(String num_factura) {
	this.num_factura = num_factura;
}

public String getFecha_emision() {
	return fecha_emision;
}

public void setFecha_emision(String fecha_emision) {
	this.fecha_emision = fecha_emision;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public Proveedor getProveedor() {
	return proveedor;
}

public void setProveedor(Proveedor proveedor) {
	this.proveedor = proveedor;
}

public Pedido getPedido() {
	return pedido;
}

public void setPedido(Pedido pedido) {
	this.pedido = pedido;
}

public List<Det_Factura> getDetallesFactura() {
	return detallesFactura;
}

public void setDetallesFactura(List<Det_Factura> detallesFactura) {
	this.detallesFactura = detallesFactura;
}


}
