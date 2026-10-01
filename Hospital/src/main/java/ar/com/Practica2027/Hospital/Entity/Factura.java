package ar.com.Practica2027.Hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Factura {
@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private Proveedor proveedor;
private Pedido pedido;
private int num_factura;
private String fecha_emision;
private double total; 
}
