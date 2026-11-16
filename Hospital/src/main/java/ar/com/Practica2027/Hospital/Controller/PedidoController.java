package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Pedido;
import ar.com.Practica2027.Hospital.Entity.Solicitud;
import ar.com.Practica2027.Hospital.Service.IPedido;
import ar.com.Practica2027.Hospital.Service.ISolicitud;
import ar.com.Practica2027.Hospital.ServiceImple.PedidoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoController {

	@Autowired
	IPedido pedidoservice;
	
	@Autowired
	ISolicitud solicitudService;
	@GetMapping("/GenerarPedido")
	public String MostrarGenerarPedido(@RequestParam("idSolicitud") int ididSolicitud,Model model) {
		Solicitud solicitudEncontrado=solicitudService.buscarporid(ididSolicitud);
		if(solicitudEncontrado==null) {
			return "redirect:/ControlSolicitud";
		}
		model.addAttribute("solicitud", solicitudEncontrado);
		model.addAttribute("detallesSolicitud", solicitudService.listadedetalles(ididSolicitud));
		return"Pedido/GenerarPedido";
	}
	
	@PostMapping("/GuardarPedido")
	public String GuardarPedido(@RequestParam("idSolicitud") int idSolicitud,
			@RequestParam("productoIds") List<Integer> productoIds,@RequestParam("cantidades") List<Double> cantidad
			,HttpSession session,RedirectAttributes redirect) {
		Empleado empleadologueado=(Empleado) session.getAttribute("empleadologueado");
		redirect.addFlashAttribute("mensaje", pedidoservice.GenerarPedido(idSolicitud,productoIds, cantidad, empleadologueado)); 
		return "redirect:/ControlSolicitud";
	}
	
	@GetMapping("/ListaPedido")
	public String MostrarlistaPedido(Model model) {
		model.addAttribute("listaPedidos", pedidoservice.listarPedido());
		return "Pedido/ListaPedido";
	}
	
	@GetMapping("/detallePedido")
	public String verDetallePedido(@RequestParam("idPedido") int idPedido, Model model) {
	    Pedido pedido = pedidoservice.BuscarporId(idPedido);
	    model.addAttribute("pedido", pedido);
	    model.addAttribute("detallesPedido", pedidoservice.listarDetallePedido(idPedido));
	    model.addAttribute("detallesSolicitud", solicitudService.listadedetalles(pedido.getSolicitud().getId()));
	    return "Pedido/DetallePedido";
	}
}
