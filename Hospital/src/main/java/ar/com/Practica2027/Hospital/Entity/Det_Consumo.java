package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Consumo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private Producto producto;
	private int cantidad;
	private Turno turno;
	
	@ManyToOne
	@JoinColumn(name = "id_consumo")
	private Consumo consumo;
	
}