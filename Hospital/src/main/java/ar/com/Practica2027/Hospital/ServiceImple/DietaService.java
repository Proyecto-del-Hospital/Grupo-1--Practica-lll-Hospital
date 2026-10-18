package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.Practica2027.Hospital.Entity.Det_Dieta;
import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.DietaMovimiento;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Entity.Sala;
import ar.com.Practica2027.Hospital.Repository.IDetDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IMovimientoDietaRepository;
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
	@Autowired
	private IMovimientoDietaRepository MovimientoRep;
	
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
		
		if (dieta.getPaciente() == null || dieta.getPaciente().getId() == 0) {
	        return "Debe seleccionar un paciente válido.";
	    }
		if (patologiasid == null || patologiasid.isEmpty()) {
			return "No seleccionaste una patología/enfermedad";
		}
		
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
		}else {
			return "No seleccionaste o agregaste una sala al paciente";
			}
		

		if (dieta.getFechaFin() != null && dieta.getFechaFin().isEmpty()) {
			dieta.setFechaFin("en tratamiento");
		}

		Estado estado = estadoRep.findBynombre("Habilitada");
		if (estado == null) {
			estado = new Estado();
			estado.setNombre("Habilitada");
			estadoRep.save(estado);
		}

		Paciente paciente = dieta.getPaciente();
		dieta.setPaciente(paciente);
		dieta.setEstado(estado);
		dietaRep.save(dieta);

		for (int id : patologiasid) {
			Patologia patologia = patologiaRep.findByid(id);
			Det_Dieta detalle = new Det_Dieta();
			detalle.setDieta(dieta);
			detalle.setPatologia(patologia);
			detdietaRep.save(detalle);
		}
		
		//se Guardara el movimento realizado o accion
		DietaMovimiento movDieta = new DietaMovimiento();
		movDieta.setFechadelcambio(LocalDate.now().toString());
		movDieta.setTipoaccion("Se Registro una Dieta");
		movDieta.setDieta(dieta);
		MovimientoRep.save(movDieta);
		return "Dieta registrada correctamente.";

	}

	@Override
	public List<Dieta> ListarDieta() {
		return dietaRep.findAll();
	}

	@Override
	public List<Dieta> BuscarDietaPorDni(String dni) {
		return dietaRep.findByPacienteDni(dni);
	}

	@Override
	public Dieta BuscarDietaXid(int id) {
		return dietaRep.findByid(id);
	}

	@Override
	public String ModificarDieta(Dieta dieta, List<Integer> patologiasid, String SalaNueva, Integer SalaId) {
		Dieta dietaExistente = dietaRep.findByid(dieta.getId());
		if (dietaExistente != null) {
			
			if (dieta.getPaciente() == null || dieta.getPaciente().getId() == 0) {
		        return "Debe seleccionar un paciente válido.";
		    }
			Paciente paciente = dieta.getPaciente();
			dietaExistente.setPaciente(paciente);
			if (SalaId != null && SalaNueva != null && !SalaNueva.isEmpty()) {
				return "No puede seleccionar una sala y escribir una nueva al mismo tiempo";
			}
			
			if (SalaId != null) {
				Sala sala = salaRep.findByid(SalaId);
				dietaExistente.setSala(sala);

			} else if (SalaNueva != null && !SalaNueva.isEmpty()) {
				Sala sala = salaRep.findBynombre(SalaNueva);
				if (sala == null) {
					sala = new Sala();
					sala.setNombre(SalaNueva);
					salaRep.save(sala);
				}
				dietaExistente.setSala(sala);
			}

		
			if (dieta.getEstado() != null && dieta.getEstado().getNombre() != null
			        && !dieta.getEstado().getNombre().isEmpty()) {
			    String nombreEstado = dieta.getEstado().getNombre();
			    
			    Estado estadoExistente = estadoRep.findBynombre(nombreEstado); 
			    if (estadoExistente == null) {
			        estadoExistente = new Estado();
			        estadoExistente.setNombre(nombreEstado);
			        estadoExistente = estadoRep.save(estadoExistente);
			    }
			    
			    dietaExistente.setEstado(estadoExistente);
			}

			    
			    String estadoActual = dietaExistente.getEstado().getNombre();
			    
			    if (estadoActual.equalsIgnoreCase("Habilitada")) {
			        dietaExistente.setFechaFin("en tratamiento");
			        
			    } else  {
			        dietaExistente.setFechaFin(LocalDate.now().toString());
			    }
			
			
			dietaExistente.setFechaInicio(dieta.getFechaInicio());
			dietaExistente.setMotivo(dieta.getMotivo());
			dietaRep.save(dietaExistente);

			if (patologiasid != null && !patologiasid.isEmpty()) {
				List<Det_Dieta> detallesAnteriores = detdietaRep.findByDietaId(dietaExistente.getId());
				if (detallesAnteriores != null && !detallesAnteriores.isEmpty()) {
					detdietaRep.deleteAll(detallesAnteriores);
				}

				if (patologiasid != null && !patologiasid.isEmpty()) {
					for (Integer id : patologiasid) {
						Patologia patologia = patologiaRep.findByid(id);
						if (patologia != null) {
							Det_Dieta detalle = new Det_Dieta();
							detalle.setDieta(dietaExistente);
							detalle.setPatologia(patologia);
							detdietaRep.save(detalle);
						}
					}
				}
			}
			DietaMovimiento movDieta = new DietaMovimiento();
			movDieta.setFechadelcambio(LocalDate.now().toString());
			movDieta.setTipoaccion("Se Modifico la Dieta");
			movDieta.setDieta(dieta);
			MovimientoRep.save(movDieta);
			return "Dieta modificada correctamente.";
			
		}
		return "No se encontró la dieta a modificar.";
	}

	@Override
	public List<Dieta> BuscarPorEstado(String estado) {
		return dietaRep.findByEstadoNombre(estado);
	}

	@Override
	public List<DietaMovimiento> ListarMovimientoDietas(int idDieta) {
		Dieta MiDieta = BuscarDietaXid(idDieta);
		if(MiDieta!=null) {
			return MiDieta.getMovimientos();
		}
		return new ArrayList<>();
	}

	@Override
	public void CambiarEstado(Dieta dieta) {
		Dieta dietaExistente = BuscarDietaXid(dieta.getId());
		Estado nuevoEstado;
		if(dietaExistente.getEstado().getNombre().equalsIgnoreCase("Habilitada")) {
			nuevoEstado = estadoRep.findBynombre("Finalizada");
			if (nuevoEstado == null) {
				nuevoEstado = new Estado();
				nuevoEstado.setNombre("Finalizada");
				nuevoEstado = estadoRep.save(nuevoEstado);
				
			}
			
			}else {
				nuevoEstado = estadoRep.findBynombre("Habilitada");
				if (nuevoEstado == null) {
					nuevoEstado = new Estado();
					nuevoEstado.setNombre("Habilitada");
					nuevoEstado = estadoRep.save(nuevoEstado);
				}
			}
		dietaExistente.setEstado(nuevoEstado);
		String nombreEstado =nuevoEstado.getNombre();
		
		if (nombreEstado.equalsIgnoreCase("Finalizada") || nombreEstado.equalsIgnoreCase("Suspendida")) {
	        // Si el estado es Finalizada o Suspendida, guardamos la fecha actual
	        dietaExistente.setFechaFin(LocalDate.now().toString());
		}
		
		dietaRep.save(dietaExistente);
		DietaMovimiento movDieta = new DietaMovimiento();
		movDieta.setFechadelcambio(LocalDate.now().toString());
		movDieta.setTipoaccion("Se Guardo el estado: "+ dietaExistente.getEstado().getNombre());
		movDieta.setDieta(dieta);
		MovimientoRep.save(movDieta);
	
	}
}
