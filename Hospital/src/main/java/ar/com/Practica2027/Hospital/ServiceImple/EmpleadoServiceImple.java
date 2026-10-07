package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Contacto;
import ar.com.Practica2027.Hospital.Entity.Direccion;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Rol;
import ar.com.Practica2027.Hospital.Entity.Usuario;
import ar.com.Practica2027.Hospital.Repository.IContactoRepository;
import ar.com.Practica2027.Hospital.Repository.IDireccionRepository;
import ar.com.Practica2027.Hospital.Repository.IEmpleadoRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IRolRepository;
import ar.com.Practica2027.Hospital.Repository.IUsuarioRepository;
import ar.com.Practica2027.Hospital.Service.IEmpleadoService;

@Service
public class EmpleadoServiceImple implements IEmpleadoService{
	
	@Autowired
	private IEmpleadoRepository EmpleRepo;

	@Autowired
	private IRolRepository rolRepo;
	
	@Autowired
	private IDireccionRepository direccionRepo;
	
	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private IEstadoRepository estadoRepo;
	
	@Autowired
	private IContactoRepository contactoRepo;
	
	@Override
	public String CrearEmpleado(Empleado empleado) {

		
		Rol rol = rolRepo.save(empleado.getUsuario().getRol());
		Usuario usuario = usuarioRepo.save(empleado.getUsuario());
		Direccion direccion = direccionRepo.save(empleado.getDireccion());
		Contacto contacto = contactoRepo.save(empleado.getContacto());
		Estado estado = estadoRepo.save(empleado.getEstado());
		
		usuario.setRol(rol);
		empleado.setUsuario(usuario);
		empleado.setDireccion(direccion);
		empleado.setContacto(contacto);
		empleado.setEstado(estado);

		EmpleRepo.save(empleado);
		return "";
	}

	@Override
	public void CrearRol(Rol rol) {
		
		rolRepo.save(rol);
		
	}

	@Override
	public List<Rol> ListaRoles() {
		return rolRepo.findAll();
	}
}
