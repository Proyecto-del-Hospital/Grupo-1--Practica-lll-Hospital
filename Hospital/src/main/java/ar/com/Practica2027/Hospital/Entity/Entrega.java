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
public class Entrega {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fecha;
	private String hora;

	@ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
	
	@OneToMany(mappedBy = "entrega")
    private List<Det_Entrega> det_entrega = new ArrayList<>();

	public Entrega() {
		super();
	}

	public Entrega(int id, String fecha, String hora, Empleado empleado, List<Det_Entrega> det_entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.empleado = empleado;
		this.det_entrega = det_entrega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Det_Entrega> getDet_entrega() {
		return det_entrega;
	}

	public void setDet_entrega(List<Det_Entrega> det_entrega) {
		this.det_entrega = det_entrega;
	}
	
    
}
