package ar.com.Practica2027.Hospital.ServiceImple;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Contacto;
import ar.com.Practica2027.Hospital.Entity.Det_Sector;
import ar.com.Practica2027.Hospital.Entity.Direccion;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Rol;
import ar.com.Practica2027.Hospital.Entity.Sector;
import ar.com.Practica2027.Hospital.Entity.Usuario;
import ar.com.Practica2027.Hospital.Repository.IContactoRepository;
import ar.com.Practica2027.Hospital.Repository.IDetSectorRepository;
import ar.com.Practica2027.Hospital.Repository.IDireccionRepository;
import ar.com.Practica2027.Hospital.Repository.IEmpleadoRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IRolRepository;
import ar.com.Practica2027.Hospital.Repository.ISectorRepository;
import ar.com.Practica2027.Hospital.Repository.IUsuarioRepository;
import ar.com.Practica2027.Hospital.Service.IEmpledo;

@Service
public class EmpleadoService implements IEmpledo{

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
	
	@Autowired
	private ISectorRepository sectorRepo;
	
	@Autowired
	private IDetSectorRepository DetSectorRepo; 
	
	@Override
	public String CrearEmpleado(Empleado empleado, List<Integer> sectoresids) {
		
		List<Empleado> empleados = EmpleRepo.findAll();
		if (sectoresids == null || sectoresids.isEmpty()) {
			
			return "No Selecionaste Un Sector";
		}
		// se agrego la validadcion de que no se repita el dni
		for (Empleado e : empleados) {
		  if (e.getDNI().equalsIgnoreCase(empleado.getDNI()) ) {
		return "El dni ya existe";
		}
		// se agrego la validadcion de que no se repita el usuario
		if (e.getUsuario().getNombre_usuario().equalsIgnoreCase(empleado.getUsuario().getNombre_usuario())) {
			return "El Usuario ya Existe";
			}
		}

		Rol rol = rolRepo.findBynombre(empleado.getUsuario().getRol().getNombre());
		if(rol == null ) { 
			rol = new Rol();
			rol.setNombre(empleado.getUsuario().getRol().getNombre());
			rolRepo.save(rol);
		}
		
		
		empleado.getUsuario().setRol(rol);
		
		Usuario usuario = usuarioRepo.save(empleado.getUsuario());
		Direccion direccion = direccionRepo.save(empleado.getDireccion());
		Contacto contacto = contactoRepo.save(empleado.getContacto());
		Estado estado = estadoRepo.findBynombre("Habilitado");
		
		if (estado == null ) {
			estado = new Estado();
			estado.setNombre("Habilitado");
			estadoRepo.save(estado);
		}
		empleado.setUsuario(usuario);
		empleado.setDireccion(direccion);
		empleado.setContacto(contacto);
		empleado.setEstado(estado);
		empleado.setMotivo("-");
		EmpleRepo.save(empleado);
		
		for (Integer id : sectoresids) {
			Sector sector = sectorRepo.findByid(id);
			Det_Sector detallesector = new Det_Sector();
			detallesector.setEmpleado(empleado);
			detallesector.setSector(sector);
			DetSectorRepo.save(detallesector);
		}
		
		return "Empleado Registrado Con Exito";
	}

	@Override
	public List<Sector> listarSector() {
		
		return sectorRepo.findAll();
	}

	@Override
	public String guardarsector(Sector sector) {
		sectorRepo.save(sector);
		return null;
	}

	@Override
	public List<Empleado> ListarEmpleados() {
		// TODO Auto-generated method stub
		return EmpleRepo.findAll();
	}
	//se agrego la subtares de buscar por dni
	@Override
	public List<Empleado> buscarEmpleado(String DNI) {
		
		return EmpleRepo.findByDNI(DNI);
	}

}
