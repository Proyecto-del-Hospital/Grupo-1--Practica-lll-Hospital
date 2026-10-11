package ar.com.Practica2027.Hospital.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;

	@OneToMany(mappedBy="sector")
	private List<Det_Sector> det_sector = new ArrayList<>();

	public Sector(int id, String nombre, List<Det_Sector> det_sector) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.det_sector = det_sector;
	}

	public Sector() {
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

	public List<Det_Sector> getDet_sector() {
		return det_sector;
	}

	public void setDet_sector(List<Det_Sector> det_sector) {
		this.det_sector = det_sector;
	}

	
}
