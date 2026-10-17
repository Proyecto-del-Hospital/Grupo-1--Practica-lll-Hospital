package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.ServiceImple.ProductoService;

@Controller
public class ProductoController {
	@Autowired
	private ProductoService productoserv;
	
	@GetMapping ("/controlproducto")
	public String ControlP (Model model) {
		model.addAttribute("producto", new Producto());
		return "Producto/ControlProducto";
	}
	@PostMapping ("/guardarproducto")
	public String GuardarProducto (Producto producto,RedirectAttributes redirect) {
		
		redirect.addFlashAttribute("exito", productoserv.CrearProducto(producto));
		
		return "redirect:/controlproducto";
	}
}
