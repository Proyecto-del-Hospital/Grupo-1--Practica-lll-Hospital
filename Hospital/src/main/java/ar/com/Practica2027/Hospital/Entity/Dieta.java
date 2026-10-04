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
public class Dieta {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 private String fechaInicio;
 private String fechaFin;
 private String descripcion;
 private String motivo;
 
 @ManyToOne
 @JoinColumn(name = "id_paciente")
 private Paciente paciente;
 
 @ManyToOne
 @JoinColumn(name = "id_sala")
 private Sala sala;
	
 @ManyToOne
 @JoinColumn(name = "id_estado")
 private Estado estado;
 
 @OneToMany(mappedBy = "dieta")
 private List<Det_Dieta> detdietas = new ArrayList<>();
 
 @OneToMany(mappedBy = "dieta")
 private List<DietaMovimiento> movimientos = new ArrayList<>();

 public Dieta(int id, String fechaInicio, String fechaFin, String descripcion, String motivo, Paciente paciente,
		Sala sala, Estado estado, List<Det_Dieta> detdietas, List<DietaMovimiento> movimientos) {
	super();
	this.id = id;
	this.fechaInicio = fechaInicio;
	this.fechaFin = fechaFin;
	this.descripcion = descripcion;
	this.motivo = motivo;
	this.paciente = paciente;
	this.sala = sala;
	this.estado = estado;
	this.detdietas = detdietas;
	this.movimientos = movimientos;
 }

 public Dieta() {
	super();
 }

 public int getId() {
	return id;
 }

 public void setId(int id) {
	this.id = id;
 }

 public String getFechaInicio() {
	return fechaInicio;
 }

 public void setFechaInicio(String fechaInicio) {
	this.fechaInicio = fechaInicio;
 }

 public String getFechaFin() {
	return fechaFin;
 }

 public void setFechaFin(String fechaFin) {
	this.fechaFin = fechaFin;
 }

 public String getDescripcion() {
	return descripcion;
 }

 public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
 }

 public String getMotivo() {
	return motivo;
 }

 public void setMotivo(String motivo) {
	this.motivo = motivo;
 }

 public Paciente getPaciente() {
	return paciente;
 }

 public void setPaciente(Paciente paciente) {
	this.paciente = paciente;
 }

 public Sala getSala() {
	return sala;
 }

 public void setSala(Sala sala) {
	this.sala = sala;
 }

 public Estado getEstado() {
	return estado;
 }

 public void setEstado(Estado estado) {
	this.estado = estado;
 }

 public List<Det_Dieta> getDetdietas() {
	return detdietas;
 }

 public void setDetdietas(List<Det_Dieta> detdietas) {
	this.detdietas = detdietas;
 }

 public List<DietaMovimiento> getMovimientos() {
	return movimientos;
 }

 public void setMovimientos(List<DietaMovimiento> movimientos) {
	this.movimientos = movimientos;
 }
 
 
 
 
}
