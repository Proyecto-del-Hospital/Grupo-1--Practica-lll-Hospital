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
	public void CrearProveedor(Proveedor proveedor) {

		List<Proveedor> proveedores = proveedorRepo.findAll();
		boolean encontrado = false;
		for (Proveedor pr : proveedores) {
			if (pr.getDni().equalsIgnoreCase(proveedor.getDni())) {
				encontrado = true;
				break;

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
			proveedorRepo.save(proveedor);
		}

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
	public void ModificarProveedor(Proveedor proveedor) {
		Proveedor proveedorExitente= proveedorRepo.findById(proveedor.getId());
		if(proveedorExitente!=null) {
			List<Proveedor>proveedores=proveedorRepo.findAll();
			boolean encontrado=false;
			for(Proveedor prov:proveedores) {
				if(prov.getId()!= proveedorExitente.getId() && prov.getDni().equalsIgnoreCase(proveedor.getDni())){
					encontrado=true;
					break;
				}
				if(encontrado==false) {
					proveedorExitente.setNombre(proveedor.getNombre());
					proveedorExitente.setApellido(proveedor.getApellido());
					proveedorExitente.setDni(proveedor.getDni());
					proveedorExitente.setMotivo(proveedor.getMotivo());
					
					Direccion direccionExistente= proveedorExitente.getDireccion();
					direccionExistente.setCalle(proveedor.getDireccion().getCalle());
					direccionExistente.setNumer_calle(proveedor.getDireccion().getNumer_calle());
					direccionExistente.setBarrio(proveedor.getDireccion().getBarrio());
					direccionRepo.save(direccionExistente);
					
					Contacto contactoExistente=proveedorExitente.getContacto();
					contactoExistente.setCorreo(proveedor.getContacto().getCorreo());
					contactoExistente.setTelefono(proveedor.getContacto().getTelefono());
					contactoRepo.save(contactoExistente);
					
				  proveedorRepo.save(proveedorExitente);
				}
			}
			
		}
		
	}

	@Override
	public Proveedor BuscarProveedor(int id) {
		
		return proveedorRepo.findById(id);
	}

	


	

}
