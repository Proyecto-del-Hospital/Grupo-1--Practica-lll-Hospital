package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Det_Entrega {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private int cantidad;
	
	@ManyToOne
    @JoinColumn(name = "id_entrega")
    private Entrega entrega;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
}
