package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Solicitud;
import ar.com.Practica2027.Hospital.Service.IPedido;
import ar.com.Practica2027.Hospital.Service.ISolicitud;
import jakarta.servlet.http.HttpSession;

@Controller
public class PedidoController {

	@Autowired
	IPedido pedioservice;
	
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
	public String GuardarPedido(@RequestParam("idSolicitud") int idSolicitud,@RequestParam("motivo") String motivo,
			@RequestParam("productoIds") List<Integer> productoIds,@RequestParam("cantidades") List<Double> cantidad
			,HttpSession session) {
		Empleado empleadologueado=(Empleado) session.getAttribute("empleadologueado");
		pedioservice.GenerarPedido(idSolicitud, motivo, productoIds, cantidad, empleadologueado);
		return "redirect:/ControlSolicitud";
	}
}
