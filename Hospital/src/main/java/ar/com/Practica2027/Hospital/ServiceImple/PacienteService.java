package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.Practica2027.Hospital.Entity.Contacto;
import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.DietaMovimiento;
import ar.com.Practica2027.Hospital.Entity.Direccion;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Repository.IContactoRepository;
import ar.com.Practica2027.Hospital.Repository.IDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IDireccionRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IMovimientoDietaRepository;
import ar.com.Practica2027.Hospital.Repository.IPacienteRepository;
import ar.com.Practica2027.Hospital.Service.IPaciente;

@Service
public class PacienteService implements IPaciente {

	@Autowired
	private IPacienteRepository pacienteRepo;

	@Autowired
	private IContactoRepository contactoRepo;

	@Autowired
	private IEstadoRepository estadoRepo;

	@Autowired
	private IDireccionRepository direccionRepo;
	@Autowired
	private IDietaRepository dietaRep;
	@Autowired
	private IMovimientoDietaRepository MovimientoRep;
	
	@Override
	public void CrearPaciente(Paciente paciente) {

		List<Paciente> pacientes = pacienteRepo.findAll();

		boolean encontrado = false;

		for (Paciente p : pacientes) {
			if (p.getDni().equalsIgnoreCase(paciente.getDni())) {
				encontrado = true;
				break;
			}
		}

		if (encontrado == false) {
			Contacto contacto = contactoRepo.save(paciente.getContacto());
			Direccion direccion = direccionRepo.save(paciente.getDireccion());
			Estado estado = estadoRepo.findBynombre("Habilitado");

			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Habilitado");
				estadoRepo.save(estado);
			}

			paciente.setEstado(estado);
			paciente.setContacto(contacto);
			paciente.setDireccion(direccion);

			pacienteRepo.save(paciente);
		}

	}

	@Override
	public List<Paciente> ListarPacientes() {

		return pacienteRepo.findAll();
	}

	@Override
	public List<Paciente> buscarPaciente(String dni) {

		return pacienteRepo.findByDni(dni);
	}

	@Override
	public void ModificarPaciente(Paciente paciente) {

		Paciente pacienteExistente = pacienteRepo.findByid(paciente.getId());
		
		if (pacienteExistente != null) {

			List<Paciente> pacientes = pacienteRepo.findAll();
			boolean encontrado = false;

			for (Paciente p : pacientes) {
				if (p.getId() != pacienteExistente.getId() && p.getDni().equalsIgnoreCase(paciente.getDni())) {
					encontrado = true;
					break;
				}
			}
			if (encontrado == false) {

				pacienteExistente.setNombre(paciente.getNombre());
				pacienteExistente.setApellido(paciente.getApellido());
				pacienteExistente.setDni(paciente.getDni());
				pacienteExistente.setMotivo(paciente.getMotivo());
				pacienteExistente.setFecha_nac(paciente.getFecha_nac());

				Contacto contactoExistente = pacienteExistente.getContacto();
				contactoExistente.setCorreo(paciente.getContacto().getCorreo());
				contactoExistente.setTelefono(paciente.getContacto().getTelefono());
				contactoRepo.save(contactoExistente);

				Direccion direccionExistente = pacienteExistente.getDireccion();
				direccionExistente.setLocalidad(paciente.getDireccion().getLocalidad());
				direccionExistente.setCalle(paciente.getDireccion().getCalle());
				direccionExistente.setBarrio(paciente.getDireccion().getBarrio());
				direccionExistente.setNumer_calle(paciente.getDireccion().getNumer_calle());
				direccionRepo.save(direccionExistente);

				pacienteRepo.save(pacienteExistente);
			}
		}
	}

	@Override
	public Paciente BuscarPacienteXid(int id) {

		return pacienteRepo.findByid(id);
	}

	@Override
	public void CambiarEstado(Paciente paciente) {

		Paciente pacienteExistente = BuscarPacienteXid(paciente.getId());
		Estado nuevoEstado;

		if (pacienteExistente.getEstado().getNombre().equalsIgnoreCase("Habilitado")) {
			nuevoEstado = estadoRepo.findBynombre("Inhabilitado");

			if (nuevoEstado == null) {
				nuevoEstado = new Estado();
				nuevoEstado.setNombre("Inhabilitado");
				nuevoEstado = estadoRepo.save(nuevoEstado);
			}
		} else {
			nuevoEstado = estadoRepo.findBynombre("Habilitado");

			if (nuevoEstado == null) {
				nuevoEstado = new Estado();
				nuevoEstado.setNombre("Habilitado");
				nuevoEstado = estadoRepo.save(nuevoEstado);
			}
		}

		pacienteExistente.setEstado(nuevoEstado);
		pacienteRepo.save(pacienteExistente);
		
		if (nuevoEstado.getNombre().equalsIgnoreCase("Inhabilitado")) {
	        
	        // 1. Buscamos el estado "Suspendida" (creándolo si no existe)
	        Estado estadoSuspendido = estadoRepo.findBynombre("Suspendida");
	        if (estadoSuspendido == null) {
	            estadoSuspendido = new Estado();
	            estadoSuspendido.setNombre("Suspendida");
	            estadoSuspendido = estadoRepo.save(estadoSuspendido);
	        }
	        
	        List<Dieta> dietasDelPaciente = dietaRep.findByPacienteId(pacienteExistente.getId());
	        if (dietasDelPaciente != null) {
	            for (Dieta dieta : dietasDelPaciente) {
	                dieta.setEstado(estadoSuspendido);
	                dieta.setFechaFin(LocalDate.now().toString());
	                dietaRep.save(dieta);
	                
	                DietaMovimiento movDieta = new DietaMovimiento();
	    			movDieta.setFechadelcambio(LocalDate.now().toString());
	    			movDieta.setTipoaccion("Se suspendió la dieta por inhabilitación del paciente");
	    			movDieta.setDieta(dieta);
	    			MovimientoRep.save(movDieta);
	            }
	        }
		}
		}
}
