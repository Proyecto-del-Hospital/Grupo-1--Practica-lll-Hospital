package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Entrega;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Repository.IDetalleEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IEntregaRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoCocinaRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
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

	@Override
	public String CrearEntrega(List<Det_Entrega> detalles, Empleado empleado, String fecha, String hora) {
		if (detalles == null || detalles.isEmpty()) {
			return "No hay productos";
		}

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
		entrega = entregaRepo.save(entrega);

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

	@Override
	public List<Entrega> buscarEntregas(String fecha) {

		return entregaRepo.findByFecha(fecha);
	}

}
