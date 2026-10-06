package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Contacto;
import ar.com.Practica2027.Hospital.Entity.Direccion;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Repository.IContactoRepository;
import ar.com.Practica2027.Hospital.Repository.IDireccionRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IPacienteRepository;
import ar.com.Practica2027.Hospital.Service.IPaciente;

@Service
public class PacienteService implements IPaciente{
	
	@Autowired
	private IPacienteRepository pacienteRepo;
	@Autowired
	private IContactoRepository contactoRepo;
	
	@Autowired
	private IEstadoRepository estadoRepo;
	
	@Autowired
	private IDireccionRepository direccionRepo;
	
	//comentario para el commit 
	@Override
	public void CrearPaciente(Paciente paciente) {
		
			Contacto contacto = contactoRepo.save(paciente.getContacto());
			Direccion direccion = direccionRepo.save(paciente.getDireccion());
			Estado estado = estadoRepo.findBynombre(paciente.getEstado().getNombre());
			
			if (estado == null) {
				estado = new Estado();
				estado.setNombre(paciente.getEstado().getNombre());
				estadoRepo.save(estado);
			}
			paciente.setEstado(estado);
			paciente.setContacto(contacto);
			paciente.setDireccion(direccion);
			
			pacienteRepo.save(paciente);
				
		}

	@Override
	public List<Paciente> ListarPacientes() {
		
		return pacienteRepo.findAll();
	}

	@Override
	public void ModificarPaciente (Paciente paciente) {
		
		Paciente pacienteExistente = BuscarPaciente(paciente.getId());
		
		if (pacienteExistente != null) {
			
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
	        direccionExistente.setCalle(paciente.getDireccion().getCalle());
	        direccionExistente.setBarrio(paciente.getDireccion().getBarrio());
	        direccionExistente.setNumer_calle(paciente.getDireccion().getNumer_calle());
	        direccionRepo.save(direccionExistente);
			
			pacienteRepo.save(pacienteExistente);
	}
		
	}

	@Override
	public Paciente BuscarPaciente(Long id) {
		
		return pacienteRepo.findByid(id);
	}

	@Override
	public void CambiarEstado(Paciente paciente) {
		
		Paciente pacienteExistente = BuscarPaciente(paciente.getId());
		
		Estado nuevoEstado;

        if (pacienteExistente.getEstado().getNombre().equalsIgnoreCase("habilitado")) {

            nuevoEstado = estadoRepo.findBynombre("inhabilitado");

        } else {

            nuevoEstado = estadoRepo.findBynombre("habilitado");
        }

        pacienteExistente.setEstado(nuevoEstado);

        pacienteRepo.save(pacienteExistente);
	}
	
}
