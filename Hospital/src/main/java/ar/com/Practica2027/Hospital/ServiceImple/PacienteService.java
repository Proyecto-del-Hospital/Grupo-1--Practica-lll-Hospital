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
	
	@Override
	public void CrearPaciente(Paciente paciente) {
		
			Contacto contacto = contactoRepo.save(paciente.getContacto());
			Direccion direccion = direccionRepo.save(paciente.getDireccion());
			Estado estado = estadoRepo.save(paciente.getEstado());
			paciente.setContacto(contacto);
			paciente.setDireccion(direccion);
			paciente.setEstado(estado);
			pacienteRepo.save(paciente);
				
		}

	@Override
	public List<Paciente> ListarPacientes() {
		
		return pacienteRepo.findAll();
	}

	@Override
	public Paciente ModificarPaciente(Paciente paciente) {
		
		Paciente pacienteExistente = BuscarPaciente(paciente.getId());
		
		if (pacienteExistente != null) {
			paciente.setNombre(paciente.getNombre());
			paciente.setApellido(paciente.getApellido());
			paciente.setDni(paciente.getDni());
			paciente.setDireccion(paciente.getDireccion());
			paciente.setContacto(paciente.getContacto());
			paciente.setMotivo(paciente.getMotivo());
			pacienteRepo.save(paciente);
	}
		
		return null;
	}

	@Override
	public Paciente BuscarPaciente(Long id) {
		
		return pacienteRepo.findByid(id);
	}
	
}
