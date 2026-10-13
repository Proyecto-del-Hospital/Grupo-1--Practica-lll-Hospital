package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Entity.Sala;

public interface IDieta {
	List<Patologia>ListarPatologias();
	void CrearPatologia(Patologia patologia);
	List<Sala>ListarSala();
	public List<Dieta>ListarDieta();
	public String CrearDieta (Dieta dieta,List<Integer> patologiasid,String SalaNueva,Integer SalaId);
	List<Dieta> BuscarDietaPorDni(String dni);
	Dieta BuscarDietaXid(int id);
	String ModificarDieta(Dieta dieta,List<Integer> patologiasid,String SalaNueva,Integer SalaId);
}
