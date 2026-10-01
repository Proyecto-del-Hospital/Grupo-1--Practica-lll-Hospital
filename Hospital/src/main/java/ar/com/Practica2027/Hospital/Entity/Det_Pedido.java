package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Det_Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private Pedido pedido;
	private Producto producto;
	private int cantidad;
}
