package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Pedido;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Pedido;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.Solicitud;
import ar.com.Practica2027.Hospital.Repository.IDetPedidoRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IPedidoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
import ar.com.Practica2027.Hospital.Repository.ISolicitudRepository;
import ar.com.Practica2027.Hospital.Service.IPedido;

@Service
public class PedidoService implements IPedido {
	@Autowired
	IProductoRepository productoRepo;
	@Autowired
	IPedidoRepository pedidoRepo;
	@Autowired
	IDetPedidoRepository detPedidoRepo;
	@Autowired
	ISolicitudRepository solicitudRepo;
	@Autowired
	IEstadoRepository estadoRepo;

	@Override
	public List<Pedido> listarPedido() {
		return pedidoRepo.findAll();
	}

	@Override
	public String GenerarPedido(int idSolicitud, String motivo, List<Integer> productosIds, List<Double> cantidades,
			Empleado empleadologueado) {
		Solicitud solicitud = solicitudRepo.findByid(idSolicitud);
		if (solicitud == null || productosIds == null || productosIds.isEmpty()) {
			return "Datos invalidos para generar el pedido";
		}
		
		Pedido pedido = new Pedido();
		pedido.setFecha(LocalDate.now().toString());
		pedido.setEmpleado(empleadologueado);
		pedido.setMotivo(motivo);
		
		Estado estado = estadoRepo.findBynombre("Pendiente");
		if (estado == null) {
			estado = new Estado();
			estado.setNombre("Pendiente");
			estado = estadoRepo.save(estado);
		}
		pedido.setEstado(estado);
		pedido.setSolicitud(solicitud);
		pedido=pedidoRepo.save(pedido);
		
		for (int i = 0; i < productosIds.size(); i++) {
		Double cantidadDePedido = cantidades.get(i);
		Producto prodEncontrado= productoRepo.findByid(productosIds.get(i));
		if(prodEncontrado!=null) {
			Det_Pedido detPedido= new Det_Pedido();
			detPedido.setPedido(pedido);
			detPedido.setProducto(prodEncontrado);
			detPedido.setCantidad(cantidadDePedido);
			detPedidoRepo.save(detPedido);
		}
		
		}
		Estado estadoSolicitud = estadoRepo.findBynombre("En Proceso de Compra");
		if (estadoSolicitud == null) {
			estadoSolicitud = new Estado();
			estadoSolicitud.setNombre("En Proceso de Compra");
			estadoSolicitud = estadoRepo.save(estadoSolicitud);
		}
		solicitud.setEstado(estadoSolicitud);
		solicitudRepo.save(solicitud);
		return "Pedido Generado con exito";
	}

}
