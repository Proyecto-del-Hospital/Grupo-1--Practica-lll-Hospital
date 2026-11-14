package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Solicitud;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.Solicitud;
import ar.com.Practica2027.Hospital.Repository.IDetSolicitudRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.ISolicitudRepository;
import ar.com.Practica2027.Hospital.Service.ISolicitud;

@Service
public class SolicitudService implements ISolicitud {
	
	
 @Autowired
 ISolicitudRepository solicitudRepo;
 @Autowired
 IDetSolicitudRepository detalleSolicitudRepo;
 @Autowired
 IEstadoRepository estadoSoliictudRepo;
	@Override
    public String altaSolicitud(List<Det_Solicitud> detalles, Empleado empleados, String motivo) {
		if(detalles==null || detalles.isEmpty()) {	
		return "No hay productos en la Lista";	
		}
		List<Det_Solicitud> listaSinrepetir = new ArrayList<>(); 
		for (Det_Solicitud listavista : detalles) {
			boolean encontrado=false;
			for (Det_Solicitud sinrepetir : listaSinrepetir) {
				if (listavista.getProducto().getId() == sinrepetir.getProducto().getId()) {
					sinrepetir.setCantidad(listavista.getCantidad()+sinrepetir.getCantidad());
					encontrado=true;
				}
			}
			if (!encontrado) {
				listaSinrepetir.add(listavista);
			}
		}
		
		Estado estado= estadoSoliictudRepo.findBynombre("Pendiente");
		if(estado==null) {
			estado=new Estado();
			estado.setNombre("Pendiente");
			estado=estadoSoliictudRepo.save(estado);
			
		}
		String fechaHoy=LocalDate.now().toString();
		Solicitud solicitud= new Solicitud();
		solicitud.setFecha(fechaHoy);
		solicitud.setMotivo(motivo);
		solicitud.setEmpleado(empleados);
		solicitud.setEstado(estado);
		solicitud=solicitudRepo.save(solicitud);
		
		
		for (Det_Solicitud det_Solicitudes : listaSinrepetir) {
		det_Solicitudes.setSolicitud(solicitud);
		detalleSolicitudRepo.save(det_Solicitudes);
		}
		return "Solicitud Guardado con Existo";
	}
	
	@Override
	public List<Solicitud> listasolictudes() {
		
		return solicitudRepo.findAllByOrderByFechaDesc();
	}
	
	@Override
	public Solicitud buscarporid(int id) {
		// TODO Auto-generated method stub
		return solicitudRepo.findByid(id);
	}
	@Override
	public List<Det_Solicitud> listadedetalles(int id) {
		// TODO Auto-generated method stub
		return detalleSolicitudRepo.findBySolicitudId(id);
	}

	@Override
	public String CambiarEstadoaceptado(int id) {
		Solicitud solicitudexistente = solicitudRepo.findByid(id);
		Estado estadocambiado = estadoSoliictudRepo.findBynombre("Aceptado");
			if (estadocambiado == null) {
				estadocambiado = new Estado();
				estadocambiado.setNombre("Aceptado");
				estadoSoliictudRepo.save(estadocambiado);
			}

			solicitudexistente.setEstado(estadocambiado);
			solicitudRepo.save(solicitudexistente);
			
		return null;
	}

	@Override
	public String CambiarEstadorechazado(int id) {
		Solicitud solicitudexistente = solicitudRepo.findByid(id);
		Estado estadocambiado = estadoSoliictudRepo.findBynombre("Rechazado");
			if (estadocambiado == null) {
				estadocambiado = new Estado();
				estadocambiado.setNombre("Rechazado");
				estadoSoliictudRepo.save(estadocambiado);
			}

			solicitudexistente.setEstado(estadocambiado);
			solicitudRepo.save(solicitudexistente);
		
		return null;
	}
	
	
	
}
