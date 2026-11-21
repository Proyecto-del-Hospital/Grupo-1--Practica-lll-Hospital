package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Det_Factura;
import ar.com.Practica2027.Hospital.Entity.Det_Pedido;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Factura;
import ar.com.Practica2027.Hospital.Entity.Pedido;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.Proveedor;
import ar.com.Practica2027.Hospital.Repository.IDetPedidoRepository;
import ar.com.Practica2027.Hospital.Repository.IDetalleFacturaRepository;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IFacturaRepository;
import ar.com.Practica2027.Hospital.Repository.IPedidoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
import ar.com.Practica2027.Hospital.Service.IFactura;

@Service
public class FacturaService implements IFactura {
	@Autowired
	IFacturaRepository facturarepo;
	@Autowired
	IDetalleFacturaRepository detFacturaRepo;
	@Autowired
	IPedidoRepository pedidoRepo;
	@Autowired
	IDetPedidoRepository detPedidoRepo;
	@Autowired
	IProductoRepository productoRepo;
	@Autowired
	IEstadoRepository estadoRepo;

	@Override
	public String GuardarFactura(int idPedido, List<Integer> productosIds, List<Double> cantidades, String numFactura,
			Proveedor proveedor, List<Double> preciounitario, String motivo, String fecha) {

		Pedido pedido = pedidoRepo.findByid(idPedido);
		pedido.setMotivo(motivo);

		Factura factura = new Factura();
		factura.setFechaemision(fecha);
		factura.setProveedor(proveedor);
		factura.setNumfactura(numFactura);
		factura.setPedido(pedido);

		List<Factura> facturas = facturarepo.findAll();

		for (Factura f : facturas) {
			if (f.getNumfactura().equalsIgnoreCase(numFactura)) {
				return "La factura ya existe";
			}
		}

		factura = facturarepo.save(factura);

		double total = 0;

		boolean pedidoCompleto = true;

		for (int i = 0; i < productosIds.size(); i++) {

			double cantidDeFactura = cantidades.get(i);
			double precioU = preciounitario.get(i);
			double subtotal = cantidDeFactura * precioU;

			Producto prodEncontrado = productoRepo.findByid(productosIds.get(i));

			double nuevoStock = prodEncontrado.getStockactual() + cantidades.get(i);

			prodEncontrado.setStockactual(nuevoStock);
			productoRepo.save(prodEncontrado);

			if (prodEncontrado != null) {
				Det_Factura detFactura = new Det_Factura();
				detFactura.setFactura(factura);
				detFactura.setCantidad(cantidDeFactura);
				detFactura.setPreciounitario(precioU);
				detFactura.setProducto(prodEncontrado);
				detFactura.setSubtotal(subtotal);
				total += subtotal;

				detFacturaRepo.save(detFactura);

				List<Det_Pedido> detallesP = detPedidoRepo.findByPedidoId(idPedido);

				for (Det_Pedido det : detallesP) {
					if (det.getProducto().getId() == productosIds.get(i)) {
						if (detFactura.getCantidad() < det.getCantidad()) {
							pedidoCompleto = false;
						}
						break;
					}
				}
			}
		}
		factura.setTotal(total);
		facturarepo.save(factura);

		if (!pedidoCompleto) {
			Estado estado = estadoRepo.findBynombre("Recibida Parcialmente");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Recibida Parcialmente");
				estado = estadoRepo.save(estado);
			}
			pedido.setEstado(estado);
			pedidoRepo.save(pedido);
		} else {
			Estado estado = estadoRepo.findBynombre("Completado");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Completado");
				estado = estadoRepo.save(estado);
			}
			pedido.setEstado(estado);
			pedidoRepo.save(pedido);
		}
		return "Factura Creada Exitosamente";
	}

	@Override
	public List<Factura> listarfacturas() {
		
		return facturarepo.findAll();
	}

	@Override
	public Factura buscarFacturaID(int id) {
		
		return facturarepo.findById(id);
	}

	@Override
	public Factura buscarFacturaNumero(String numfactura) {
		
		return facturarepo.findBynumfactura(numfactura);
	}

}
