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
public class Consumo {
	@Id
	
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String fecha;
	
	@OneToMany
	private List<Det_Consumo> det_consumo = new ArrayList<>();
	
}
