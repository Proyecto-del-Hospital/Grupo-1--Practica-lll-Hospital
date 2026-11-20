package ar.com.Practica2027.Hospital.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Pedido;
import ar.com.Practica2027.Hospital.Entity.Proveedor;
import ar.com.Practica2027.Hospital.Service.IFactura;
import ar.com.Practica2027.Hospital.Service.IPedido;
import ar.com.Practica2027.Hospital.Service.IProveedor;

@Controller
public class FacturaController {

	@Autowired
	IFactura facturaServ;
	@Autowired
	IPedido pedidoServ;
	@Autowired
	IProveedor proveedorServ;

	@GetMapping("/Generarfactura")
	public String generarfactura(Model model, @RequestParam("idPedido") int idPedido) {
		Pedido pedidoExistente = pedidoServ.BuscarporId(idPedido);
		if (pedidoExistente == null) {
			return "redirect:/GenerarPedido";
		}
		model.addAttribute("pedido", pedidoExistente);
		model.addAttribute("detallesPedido", pedidoServ.listarDetallePedido(idPedido));
		model.addAttribute("ListaProveedores", proveedorServ.ListarProveedores());
		

		return "Factura/GenerarFactura";
	}

	@PostMapping("/GuardarFactura")
	public String guardarfactura(@RequestParam("idPedido") int idPedido,@RequestParam("productoIds") List<Integer> productosIds,
			@RequestParam("cantidades") List<Double> cantidades,String numfactura,
			@RequestParam("id_proveedor") int id_proveedor, @RequestParam("preciounitario") List<Double> preciounitario,
			 String motivo, String fechaemision, RedirectAttributes redirect) {

		Proveedor proveedorExistente = proveedorServ.BuscarProveedor(id_proveedor);

		redirect.addFlashAttribute("mensaje", facturaServ.GuardarFactura(idPedido, productosIds, cantidades, numfactura,
				proveedorExistente, preciounitario, motivo, fechaemision));
		return "redirect:/ListaPedido";
	}

}
