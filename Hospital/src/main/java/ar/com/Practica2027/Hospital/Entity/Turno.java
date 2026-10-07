package ar.com.Practica2027.Hospital.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Turno {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
  @OneToMany(mappedBy="turno")
  private List<Det_Consumo> detconsumo=new ArrayList<>();

public Turno() {
	super();
}

public Turno(int id, String nombre, List<Det_Consumo> detconsumo) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.detconsumo = detconsumo;
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

public List<Det_Consumo> getDetconsumo() {
	return detconsumo;
}

public void setDetconsumo(List<Det_Consumo> detconsumo) {
	this.detconsumo = detconsumo;
}
  
	
}
