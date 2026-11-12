package ar.com.Practica2027.Hospital.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Consumo;
import ar.com.Practica2027.Hospital.Entity.Det_Consumo;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Service.IConsumo;
import ar.com.Practica2027.Hospital.Service.IProductoCocina;
import jakarta.servlet.http.HttpSession;

@Controller
public class ConsumoController {

	@Autowired
	IConsumo consumoService;
	@Autowired
	IProductoCocina productoCService;
	
	@GetMapping("/LimpiarControlConsumo")
	public String LimpiarConsumo(HttpSession session) {
		List<Det_Consumo> listDetallesConsumo = (List<Det_Consumo>) session.getAttribute("listaTemporalC");
		session.removeAttribute("listaTemporalC");
		
		return "redirect:/ControlConsumo";
	}
	@GetMapping("/ControlConsumo")
	public String MostrarControlConsumo(Model model,HttpSession session) {
		List<Det_Consumo> listDetallesConsumo = (List<Det_Consumo>) session.getAttribute("listaTemporalC");
		if(listDetallesConsumo==null) {
			listDetallesConsumo = new ArrayList<>();
			session.setAttribute("listaTemporalC",listDetallesConsumo);
		}
		model.addAttribute("listaProductosC", productoCService.ListarProductosC());
		model.addAttribute("listaDetallesC", listDetallesConsumo);
	return "Consumo/ControlConsumo";
	}
	
	@PostMapping("/agregarProductoConsumo")
	public String agregarProductoTemporal(HttpSession session,@RequestParam("productoIdC") int productoIdC,@RequestParam("cantidad") double cantidad
			,RedirectAttributes redirect) {
		List<Det_Consumo> listDetallesConsumo = (List<Det_Consumo>) session.getAttribute("listaTemporalC");
		
		ProductoCocina producto=productoCService.buscarProductocPorId(productoIdC);
		if (producto != null) {
	      
	        if (cantidad > producto.getStockactual()) {
	            redirect.addFlashAttribute("mensaje", 
	                "La cantidad ingresada (" + cantidad + ") supera el stock disponible (" + producto.getStockactual() + ")");
	            return "redirect:/ControlConsumo";
	        }
	        
		Det_Consumo det_consumo = new Det_Consumo();
		det_consumo.setProductococina(producto);
		det_consumo.setCantidad(cantidad);
		listDetallesConsumo.add(det_consumo);
		session.setAttribute("listaTemporalC", listDetallesConsumo);
		
	}
		return "redirect:/ControlConsumo";
	}
	
	@PostMapping("/GuardarConsumo")
	public String GuardarSolicitud(HttpSession session,@RequestParam ("turno") String turno) {
		List<Det_Consumo> listDetallesConsumo = (List<Det_Consumo>) session.getAttribute("listaTemporalC");
		Empleado empleadologueado=(Empleado)session.getAttribute("empleadologueado");
		session.removeAttribute("ListaTemporal");
		consumoService.registrarConsuDiario(listDetallesConsumo, empleadologueado, turno);
		session.removeAttribute("listaTemporalC");
	return"redirect:/ControlConsumo";
	}
	

}
