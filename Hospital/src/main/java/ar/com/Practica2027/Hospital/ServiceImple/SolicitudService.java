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
		
		
		for (Det_Solicitud det_Solicitudes : detalles) {
		det_Solicitudes.setSolicitud(solicitud);
		detalleSolicitudRepo.save(det_Solicitudes);
		}
		return "Solicitud Guardado con Existo";
	}
	
	
	
}
