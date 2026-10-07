package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DietaMovimiento {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String fechadelcambio;
	private String tipoaccion;
	
	@ManyToOne
    @JoinColumn(name = "id_dieta")
    private Dieta dieta;
	
	@ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

	public DietaMovimiento(int id, String fechadelcambio, String tipoaccion, Dieta dieta, Empleado empleado) {
		super();
		this.id = id;
		this.fechadelcambio = fechadelcambio;
		this.tipoaccion = tipoaccion;
		this.dieta = dieta;
		this.empleado = empleado;
	}

	public DietaMovimiento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechadelcambio() {
		return fechadelcambio;
	}

	public void setFechadelcambio(String fechadelcambio) {
		this.fechadelcambio = fechadelcambio;
	}

	public String getTipoaccion() {
		return tipoaccion;
	}

	public void setTipoaccion(String tipoaccion) {
		this.tipoaccion = tipoaccion;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	
}
