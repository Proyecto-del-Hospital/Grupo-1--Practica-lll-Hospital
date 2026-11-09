package ar.com.Practica2027.Hospital.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Det_Consumo;
import ar.com.Practica2027.Hospital.Entity.Det_Solicitud;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Service.IProducto;
import ar.com.Practica2027.Hospital.Service.ISolicitud;
import ar.com.Practica2027.Hospital.ServiceImple.ProductoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class SolicitudController {
	
	@Autowired
	IProducto productoService;
	@Autowired
	ISolicitud solicitudService;
	@GetMapping("/LimpiarControlSolicitud")
	public String LimpiarSolicitud(HttpSession session) {
		List<Det_Solicitud>ListDetallSolicitud=(List<Det_Solicitud>) session.getAttribute("ListaTemporal");
		session.removeAttribute("ListaTemporal");
		
		return "redirect:/ControlSolicitud";
	}
	
	@GetMapping("/ControlSolicitud")
	public String mostrarSolicitud(Model model, HttpSession session){
		List<Det_Solicitud>ListDetallSolicitud=(List<Det_Solicitud>) session.getAttribute("ListaTemporal");
		if(ListDetallSolicitud==null) {
			ListDetallSolicitud=new ArrayList<>();
			session.setAttribute("ListaTemporal", ListDetallSolicitud);
			
		}
		model.addAttribute("ListDetallSolicitud", ListDetallSolicitud);
		model.addAttribute("ListaProductos", productoService.ListarProductos());
		model.addAttribute("listaSolicitudes", solicitudService.listasolictudes());
		
	return "Solicitud/ControlSolicitud";}
	
	@PostMapping("/Agregar")
	public String agregarAlaLista(@RequestParam("productoId")int productoId, @RequestParam("cantidad")double cantidad, HttpSession session) {
		List<Det_Solicitud>ListDetallSolicitud=(List<Det_Solicitud>) session.getAttribute("ListaTemporal");
		
			Producto producto=productoService.buscarProductoPorId(productoId);
			Det_Solicitud detalle=new Det_Solicitud();
			detalle.setProducto(producto);
			detalle.setCantidad(cantidad);
			ListDetallSolicitud.add(detalle);
		
		
		return "redirect:/ControlSolicitud";
	}
	@PostMapping("/GuardarSolicitud")
	public String GuardarSolicitud(HttpSession session,@RequestParam ("motivo") String motivo) {
		List<Det_Solicitud>ListDetallSolicitud=(List<Det_Solicitud>) session.getAttribute("ListaTemporal");
		Empleado empleadologueado=(Empleado)session.getAttribute("empleadologueado");
		solicitudService.altaSolicitud(ListDetallSolicitud, empleadologueado, motivo);
		session.removeAttribute("ListaTemporal");
		
	return"redirect:/ControlSolicitud";
	}
	
	@PostMapping("/QuitarProductoListaS")
	public String quitarProdListSolic(HttpSession session, @RequestParam ("posicion") int posicion) {
	List<Det_Solicitud>ListDetallSolicitud=(List<Det_Solicitud>) session.getAttribute("ListaTemporal");
	if(ListDetallSolicitud!=null && posicion>=0 ) {
	 for (int i = 0; i < ListDetallSolicitud.size(); i++) {
		 if(ListDetallSolicitud.get(i).getProducto().getId()==posicion) {
			 ListDetallSolicitud.remove(i);
			 break;
		 }
		
	}
	 session.setAttribute("ListaTemporal",ListDetallSolicitud);
	}
	
	return "redirect:/ControlSolicitud";
	}

}
