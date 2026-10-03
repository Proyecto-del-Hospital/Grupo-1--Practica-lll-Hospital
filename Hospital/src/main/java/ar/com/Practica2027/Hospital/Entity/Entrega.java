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
	
	@OneToMany
    private List<Det_Entrega> det_entrega = new ArrayList<>();
	
}
