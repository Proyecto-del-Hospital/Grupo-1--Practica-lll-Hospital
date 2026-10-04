package ar.com.Practica2027.Hospital.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patologia {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToMany(mappedBy = "patologia")
	private List<Det_Dieta> detdietas =  new ArrayList<>();

	public Patologia(int id, String nombre, List<Det_Dieta> detdietas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.detdietas = detdietas;
	}

	public Patologia() {
		super();
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

	public List<Det_Dieta> getDetdietas() {
		return detdietas;
	}

	public void setDetdietas(List<Det_Dieta> detdietas) {
		this.detdietas = detdietas;
	}
	
	
}
