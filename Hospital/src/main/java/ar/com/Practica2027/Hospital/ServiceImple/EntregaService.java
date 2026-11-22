package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Entrega;
import ar.com.Practica2027.Hospital.Entity.Det_Solicitud;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Entity.Solicitud;
import ar.com.Practica2027.Hospital.Repository.IDetSolicitudRepository;
import ar.com.Practica2027.Hospital.Repository.IDetalleEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoCocinaRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
import ar.com.Practica2027.Hospital.Repository.ISolicitudRepository;
import ar.com.Practica2027.Hospital.Service.IEntrega;

@Service
public class EntregaService implements IEntrega {

	@Autowired
	IEntregaRepository entregaRepo;
	@Autowired
	IProductoRepository productoRepo;
	@Autowired
	IDetalleEntregaRepository detalleRepo;
	@Autowired
	IProductoCocinaRepository productoCocinaRepo;
	@Autowired
	ISolicitudRepository solicitudRepo;
	@Autowired
	IDetSolicitudRepository detalleSolicitudRepo;
	@Autowired
	IEstadoRepository estadoSoliictudRepo;

	@Override
	public String CrearEntrega(int idSolicitud, List<Det_Entrega> detalles, Empleado empleado, String fecha,
			String hora) {
		if (detalles == null || detalles.isEmpty()) {
			return "No hay productos en la lista";
		}
		Solicitud solicitud = solicitudRepo.findByid(idSolicitud);
		
		List<Det_Entrega> listaSinRepetir = new ArrayList<>();
		for (Det_Entrega det_Entrega : detalles) {
			boolean encontrado = false;
			for (Det_Entrega sinRepetir : listaSinRepetir) {
				if (sinRepetir.getProducto().getId() == det_Entrega.getProducto().getId()) {
					encontrado = true;
					sinRepetir.setCantidad(sinRepetir.getCantidad() + det_Entrega.getCantidad());
					break;
				}
			}
			if (!encontrado) {
				listaSinRepetir.add(det_Entrega);
			}
		}
		for (Det_Entrega sinRepetir : listaSinRepetir) {
			Producto productoExistente = productoRepo.findByid(sinRepetir.getProducto().getId());
			if (sinRepetir.getCantidad() > productoExistente.getStockactual()) {
				return "Stock insuficiente: " + productoExistente.getNombre();
			}
		}

		Entrega entrega = new Entrega();
		entrega.setFecha(fecha);
		entrega.setHora(hora);
		entrega.setEmpleado(empleado);

		for (Det_Entrega det_Entrega : listaSinRepetir) {

			if (entrega.getEmpleado().getUsuario().getRol().getNombre().equalsIgnoreCase("Jefa de Cocina")
					|| entrega.getEmpleado().getUsuario().getRol().getNombre().equalsIgnoreCase("Cocinero")) {

				ProductoCocina productoC = productoCocinaRepo.findBynombre(det_Entrega.getProducto().getNombre());

				if (productoC == null) {
					return "Producto no cargado por la jefa de cocina";
				}

				double StockNuevo = productoC.getStockactual() + det_Entrega.getCantidad();
				productoC.setStockactual(StockNuevo);
				productoCocinaRepo.save(productoC);

			}

			Producto productoExistente = productoRepo.findByid(det_Entrega.getProducto().getId());
			double nuevoStock = productoExistente.getStockactual() - det_Entrega.getCantidad();
			productoExistente.setStockactual(nuevoStock);
			productoRepo.save(productoExistente);
			entrega = entregaRepo.save(entrega);
			det_Entrega.setEntrega(entrega);
			detalleRepo.save(det_Entrega);
			if (solicitud != null) {
				for (Det_Solicitud detSol : solicitud.getDetallesSolicitudes()) {
					if (detSol.getProducto().getId() == productoExistente.getId()) {
						detSol.setCantidadEntregado(detSol.getCantidadEntregado() + det_Entrega.getCantidad());
						detalleSolicitudRepo.save(detSol);
						break;
					}
				}
			}
		}
		if (solicitud != null) {
			boolean completa = true;
			for (Det_Solicitud detSol : solicitud.getDetallesSolicitudes()) {
				if (detSol.getCantidadEntregado() < detSol.getCantidad()) {
					completa = false;
					break;
				}
			}

			if (completa) {
				Estado estadoSolicitud = estadoSoliictudRepo.findBynombre("Entregado");
				if (estadoSolicitud == null) {
					estadoSolicitud = new Estado();
					estadoSolicitud.setNombre("Entregado");
					estadoSolicitud = estadoSoliictudRepo.save(estadoSolicitud);

				}
				solicitud.setEstado(estadoSolicitud);
			} else {
				Estado estadoSolicitud = estadoSoliictudRepo.findBynombre("Entrega Parcial");
				if (estadoSolicitud == null) {
					estadoSolicitud = new Estado();
					estadoSolicitud.setNombre("Entrega Parcial");
					estadoSolicitud = estadoSoliictudRepo.save(estadoSolicitud);

				}
				solicitud.setEstado(estadoSolicitud);
			}
			solicitudRepo.save(solicitud);

		}
		return "Entrega guardada";
	}
	@Override
	public List<Det_Entrega> GenerarListaDetalle(List<Integer> productoIds, List<Double> cantidades) {
	    List<Det_Entrega> detalles = new ArrayList<>();

	    if (productoIds == null || cantidades == null || productoIds.size() != cantidades.size()) {
	        return detalles;
	    }

	    for (int i = 0; i < productoIds.size(); i++) {
	        Double cant = cantidades.get(i);
	        if (cant != null && cant > 0) {
	            Producto prod = productoRepo.findByid(productoIds.get(i));
	            if (prod != null) {
	                Det_Entrega det = new Det_Entrega();
	                det.setProducto(prod);
	                det.setCantidad(cant);
	                detalles.add(det);
	            }
	        }
	    }

	    return detalles;
	}

	@Override
	public List<Entrega> listarEntregas() {

		return entregaRepo.findAll();
	}

	@Override
	public Entrega BuscarEntregaId(int id) {

		return entregaRepo.findById(id);
	}

	@Override
	public List<Entrega> buscarEntregas(String fecha) {

		return entregaRepo.findByFecha(fecha);
	}

}
