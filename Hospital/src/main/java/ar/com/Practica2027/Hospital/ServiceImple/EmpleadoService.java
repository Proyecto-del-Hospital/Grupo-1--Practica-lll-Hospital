package ar.com.Practica2027.Hospital.ServiceImple;


import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
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
import org.mindrot.jbcrypt.BCrypt;

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
		if (e.getUsuario().getNombreu().equalsIgnoreCase(empleado.getUsuario().getNombreu())) {
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
		
		String empleadodesencritado = empleado.getUsuario().getContrasena();
	    String empleadoencritado =  BCrypt.hashpw(empleadodesencritado, BCrypt.gensalt());
		empleado.getUsuario().setContrasena(empleadoencritado);
		
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

	@Override
	public Empleado buscarporid(int id) {
		// TODO Auto-generated method stub
		return EmpleRepo.findByid(id);
	}

	@Override
	public String modificarempleado(Empleado empleado) {
		
		Empleado empleadoExistente = EmpleRepo.findByid(empleado.getId());

	    if (empleadoExistente == null) {
	        return "El empleado no existe";
	    }

	    List<Empleado> empleados = EmpleRepo.findAll();
	    for (Empleado e : empleados) {
	        if (e.getId() != empleadoExistente.getId()) {

	            if (e.getDNI().equalsIgnoreCase(empleado.getDNI())) {
	                return "El dni ya existe";
	            }
	            if (e.getUsuario().getNombreu()
	                    .equalsIgnoreCase(empleado.getUsuario().getNombreu())) {
	                return "El Usuario ya Existe";
	            }
	        }
	    }

	    empleadoExistente.setNombre(empleado.getNombre());
	    empleadoExistente.setApellido(empleado.getApellido());
	    empleadoExistente.setDNI(empleado.getDNI());
	    empleadoExistente.setFecha_nac(empleado.getFecha_nac());

	    Contacto contactoExistente = empleadoExistente.getContacto();
	    contactoExistente.setCorreo(empleado.getContacto().getCorreo());
	    contactoExistente.setTelefono(empleado.getContacto().getTelefono());
	    contactoRepo.save(contactoExistente);

	    Direccion direccionExistente = empleadoExistente.getDireccion();
	    direccionExistente.setBarrio(empleado.getDireccion().getBarrio());
	    direccionExistente.setCalle(empleado.getDireccion().getCalle());
	    direccionExistente.setLocalidad(empleado.getDireccion().getLocalidad());
	    direccionExistente.setNumer_calle(empleado.getDireccion().getNumer_calle());
	    direccionRepo.save(direccionExistente);

	    Rol rol = rolRepo.findBynombre(empleado.getUsuario().getRol().getNombre());
	    if (rol == null) {
	        rol = new Rol();
	        rol.setNombre(empleado.getUsuario().getRol().getNombre());
	        rol = rolRepo.save(rol);
	    }

	    Usuario usuarioExistente = empleadoExistente.getUsuario();
	    usuarioExistente.setNombreu(empleado.getUsuario().getNombreu());
	    usuarioExistente.setRol(rol);
	    
	    String empleadodesencritado = empleado.getUsuario().getContrasena();
	    String empleadoencritado =  BCrypt.hashpw(empleadodesencritado, BCrypt.gensalt());
		empleadoExistente.getUsuario().setContrasena(empleadoencritado);
	    
		
	    if (empleadodesencritado != null) {
	        usuarioExistente.setContrasena(empleadoencritado);
	    }
	    
	    usuarioRepo.save(usuarioExistente);

	    EmpleRepo.save(empleadoExistente);

	    return "Empleado modificado con éxito";
	}

	@Override
	public String CambiarEstadoempleado(Empleado empleado) {
		
		Empleado empleadoexistente = EmpleRepo.findByid(empleado.getId());
		Estado nuevoEstado;

		if (empleadoexistente.getEstado().getNombre().equalsIgnoreCase("Habilitado")) {
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

		empleadoexistente.setEstado(nuevoEstado);
		EmpleRepo.save(empleadoexistente);
		
		if (nuevoEstado.getNombre().equalsIgnoreCase("Inhabilitado")) {
	        
	        Estado estadoSuspendido = estadoRepo.findBynombre("Suspendida");
	        if (estadoSuspendido == null) {
	            estadoSuspendido = new Estado();
	            estadoSuspendido.setNombre("Suspendida");
	            estadoSuspendido = estadoRepo.save(estadoSuspendido);
	        }
	      }
		return "";
		}

	@Override
	public void inicializardatos() {
		if (rolRepo.findAll().isEmpty()) {
	        Rol r1 = new Rol(); 
	        r1.setNombre("Administrador");
	        rolRepo.save(r1);
	        Rol r2 = new Rol(); 
	        r2.setNombre("Jefa de Cocina"); 
	        rolRepo.save(r2);
	        Rol r3 = new Rol(); 
	        r3.setNombre("Cocinero"); 
	        rolRepo.save(r3);
	    }

	    if (estadoRepo.findAll().isEmpty()) {
	        Estado e1 = new Estado(); 
	        e1.setNombre("Habilitado"); 
	        estadoRepo.save(e1);
	        Estado e2 = new Estado(); 
	        e2.setNombre("Inhabilitado"); 
	        estadoRepo.save(e2);
	    }

	    if (usuarioRepo.findAll().isEmpty()) {
	        
	        Direccion dir = new Direccion();
	        dir = direccionRepo.save(dir);

	        Contacto con = new Contacto();
	        con = contactoRepo.save(con);

	        Empleado admin = new Empleado();
	        admin.setNombre("Super");
	        admin.setApellido("Administrador");
	        admin.setDNI("00000000");
	        admin.setMotivo("-");
	        admin.setDireccion(dir);
	        admin.setContacto(con);
	        admin.setEstado(estadoRepo.findBynombre("Habilitado"));
	        
	        admin = EmpleRepo.save(admin);

	        Usuario usuAdmin = new Usuario();
	        usuAdmin.setNombreu("admin");
	        usuAdmin.setContrasena(BCrypt.hashpw("admin123", BCrypt.gensalt()));
	        usuAdmin.setRol(rolRepo.findBynombre("Administrador"));
	        usuAdmin.setEmpleado(admin);
	        
	        usuAdmin = usuarioRepo.save(usuAdmin);

	        admin.setUsuario(usuAdmin);
	        EmpleRepo.save(admin);
	        
	    }
	}
}
