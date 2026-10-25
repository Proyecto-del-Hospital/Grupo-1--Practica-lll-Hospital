package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Contacto;
import ar.com.Practica2027.Hospital.Entity.Direccion;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Proveedor;
import ar.com.Practica2027.Hospital.Repository.IContactoRepository;
import ar.com.Practica2027.Hospital.Repository.IDireccionRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IProveedorRepository;
import ar.com.Practica2027.Hospital.Service.IProveedor;

@Service
public class ProveedorService implements IProveedor {
	@Autowired
	private IProveedorRepository proveedorRepo;
	@Autowired
	private IContactoRepository contactoRepo;

	@Autowired
	private IEstadoRepository estadoRepo;

	@Autowired
	private IDireccionRepository direccionRepo;

	@Override
	public String CrearProveedor(Proveedor proveedor) {

		List<Proveedor> proveedores = proveedorRepo.findAll();
		boolean encontrado = false;
		for (Proveedor pr : proveedores) {
			if (pr.getDni().equalsIgnoreCase(proveedor.getDni())) {
				encontrado = true;

				return "El dni ya existe";
			}

		}
		if (encontrado == false) {

			Contacto contacto = contactoRepo.save(proveedor.getContacto());
			Estado estado = estadoRepo.findBynombre("Habilitado");
			Direccion direccion = direccionRepo.save(proveedor.getDireccion());
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Habilitado");
				estadoRepo.save(estado);
			}
			proveedor.setContacto(contacto);
			proveedor.setEstado(estado);
			proveedor.setDireccion(direccion);
			proveedor.setMotivo("-");
			proveedorRepo.save(proveedor);
		}

		return "Proveedor creado con exito";
	}

	@Override
	public List<Proveedor> ListarProveedores() {

		return proveedorRepo.findAll();
	}

	@Override
	public List<Proveedor> BuscarProveedor(String dni) {

		return proveedorRepo.findByDni(dni);
	}

	@Override
	public String ModificarProveedor(Proveedor proveedor) {
		Proveedor proveedorExistente = proveedorRepo.findByid(proveedor.getId());
	    
	    if (proveedorExistente != null) {

	        List<Proveedor> proveedores = proveedorRepo.findAll();
	        boolean encontrado = false;

	        for (Proveedor prov : proveedores) {
	            if (prov.getId() != proveedorExistente.getId() && prov.getDni().equalsIgnoreCase(proveedor.getDni())) {
	                encontrado = true;
	                break;
	            }
	        }
	        
	        if (encontrado == false) {

	            proveedorExistente.setNombre(proveedor.getNombre());
	            proveedorExistente.setApellido(proveedor.getApellido());
	            proveedorExistente.setDni(proveedor.getDni());
	            proveedorExistente.setMotivo(proveedor.getMotivo());

	            Contacto contactoExistente = proveedorExistente.getContacto();
	            contactoExistente.setCorreo(proveedor.getContacto().getCorreo());
	            contactoExistente.setTelefono(proveedor.getContacto().getTelefono());
	            contactoRepo.save(contactoExistente);

	            Direccion direccionExistente = proveedorExistente.getDireccion();
	            direccionExistente.setLocalidad(proveedor.getDireccion().getLocalidad());
	            direccionExistente.setCalle(proveedor.getDireccion().getCalle());
	            direccionExistente.setBarrio(proveedor.getDireccion().getBarrio());
	            direccionExistente.setNumer_calle(proveedor.getDireccion().getNumer_calle());
	            direccionRepo.save(direccionExistente);

	            proveedorRepo.save(proveedorExistente);
	            
	            return "Proveedor modificado con éxito";
	        }
	    }
	    
	    return "No puedes modificar DNI ya existente";
	}

	@Override
	public Proveedor BuscarProveedor(int id) {

		return proveedorRepo.findByid(id);
	}

	@Override
	public void cambiarEstadoProveedor(Proveedor proveedor) {
		Proveedor proveedorExistente = proveedorRepo.findByid(proveedor.getId());
		Estado estadoNuevo;
		if (proveedorExistente.getEstado().getNombre().equalsIgnoreCase("Habilitado")) {
			estadoNuevo = estadoRepo.findBynombre("Inhabilitado");
			if (estadoNuevo == null) {
				estadoNuevo = new Estado();
				estadoNuevo.setNombre("Inhabilitado");
				estadoNuevo = estadoRepo.save(estadoNuevo);
			}

		} else {
			estadoNuevo = estadoRepo.findBynombre("Habilitado");
			if (estadoNuevo == null) {
				estadoNuevo = new Estado();
				estadoNuevo.setNombre("Habilitado");
				estadoNuevo = estadoRepo.save(estadoNuevo);
			}
		}
		proveedorExistente.setEstado(estadoNuevo);
		proveedorRepo.save(proveedorExistente);
	}

}
