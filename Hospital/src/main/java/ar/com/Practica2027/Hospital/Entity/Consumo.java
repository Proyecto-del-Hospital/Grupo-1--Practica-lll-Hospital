package ar.com.Practica2027.Hospital.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Consumo {
	@Id
	
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String fecha;
	
	@OneToMany(mappedBy = "consumo")
	private List<Det_Consumo> det_consumo = new ArrayList<>();

	public Consumo(int id, String fecha, List<Det_Consumo> det_consumo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.det_consumo = det_consumo;
	}

	public Consumo() {
		super();
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

	public List<Det_Consumo> getDet_consumo() {
		return det_consumo;
	}

	public void setDet_consumo(List<Det_Consumo> det_consumo) {
		this.det_consumo = det_consumo;
	}
	
	
}
