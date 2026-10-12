package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Dieta;
import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Entity.Sala;
import ar.com.Practica2027.Hospital.Repository.IDetDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IPacienteRepository;
import ar.com.Practica2027.Hospital.Repository.IPatologiaRepository;
import ar.com.Practica2027.Hospital.Repository.ISalaRepository;
import ar.com.Practica2027.Hospital.Service.IDieta;

@Service
public class DietaService implements IDieta {
	@Autowired
	private IDietaRepository dietaRep;
	@Autowired
	private IDetDietaRepository detdietaRep;
	@Autowired
	private IPatologiaRepository patologiaRep;
	@Autowired
	private ISalaRepository salaRep;
	@Autowired
	private IEstadoRepository estadoRep;

	@Override
	public List<Patologia> ListarPatologias() {
		return patologiaRep.findAll();
	}

	@Override
	public void CrearPatologia(Patologia patologia) {
		patologiaRep.save(patologia);
	}

	@Override
	public List<Sala> ListarSala() {
		return salaRep.findAll();
	}

	@Override
	public String CrearDieta(Dieta dieta, List<Integer> patologiasid, String SalaNueva, Integer SalaId) {

		if (SalaId != null && SalaNueva != null && !SalaNueva.isEmpty()) {
			return "No puede seleccionar una sala y escribir una nueva al mismo tiempo";
		}

		if (SalaId != null) {
			Sala sala = salaRep.findByid(SalaId);
			dieta.setSala(sala);

		} else if (SalaNueva != null && !SalaNueva.isEmpty()) {
		    Sala sala = salaRep.findBynombre(SalaNueva);
		    if (sala == null) {
		        sala = new Sala();
		        sala.setNombre(SalaNueva);
		        salaRep.save(sala);
		    }

		    dieta.setSala(sala);

		} else {
		    return "No seleccionaste o agregaste una sala al paciente";
		}	
		
		Estado estado = estadoRep.findBynombre("Habilitado");
		if (estado == null) {
			estado = new Estado();
			estado.setNombre("Habilitado");
			estadoRep.save(estado);
		}

		Paciente paciente = dieta.getPaciente();
		dieta.setPaciente(paciente);
		dieta.setEstado(estado);
		dietaRep.save(dieta);

		for (Integer id : patologiasid) {
			Patologia patologia = patologiaRep.findByid(id);
			Det_Dieta detalle = new Det_Dieta();
			detalle.setDieta(dieta);
			detalle.setPatologia(patologia);
			detdietaRep.save(detalle);
		}
		return "Dieta registrada correctamente.";
	}


}
