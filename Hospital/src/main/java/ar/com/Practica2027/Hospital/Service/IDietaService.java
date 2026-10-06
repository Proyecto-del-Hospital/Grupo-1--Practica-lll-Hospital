package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Entity.Sala;

public interface IDietaService {
	
	public String CrearDieta (Dieta dieta,List<Integer> patologiasid,String nombreSala,Integer SalaId);
	public List<Dieta>ListarDieta();
	
	public void CrearPatologia(Patologia patologia);
	public List<Patologia>ListarPatologias();
	
	public List<Sala>ListarSala();
	
	public List<Paciente>ListarPacientes();
	
}
