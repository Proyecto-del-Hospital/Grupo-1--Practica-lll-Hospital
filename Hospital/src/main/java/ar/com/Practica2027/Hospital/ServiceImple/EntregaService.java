package ar.com.Practica2027.Hospital.ServiceImple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Entrega;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;
import ar.com.Practica2027.Hospital.Repository.IDetalleEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
import ar.com.Practica2027.Hospital.Service.IEntrega;

@Service
public class EntregaService implements IEntrega{
	
	@Autowired
	IEntregaRepository entregaRepo;
	
	@Autowired
	IProductoRepository productoRepo;
	
	@Autowired
	IDetalleEntregaRepository detalleRepo;

	@Override
	public String CrearEntrega(List<Det_Entrega> detalles, Empleado empleado, String fecha, String hora) {
		if (detalles == null || detalles.isEmpty()) {
			return "No hay productos";
		}
		Entrega entrega = new Entrega();
		entrega.setFecha(fecha);
		entrega.setHora(hora);
		entrega.setEmpleado(empleado);
		entrega = entregaRepo.save(entrega);
		
		for (Det_Entrega det_Entrega : detalles) {
			det_Entrega.setEntrega(entrega);
			detalleRepo.save(det_Entrega);
		}
		return "Entrega guardada";
	}

	@Override
	public List<Entrega> listarEntregas() {
		
		return entregaRepo.findAll();
	}

	@Override
	public Entrega BuscarEntregaId(int id) {
		
		return entregaRepo.findById(id);
	}


}
