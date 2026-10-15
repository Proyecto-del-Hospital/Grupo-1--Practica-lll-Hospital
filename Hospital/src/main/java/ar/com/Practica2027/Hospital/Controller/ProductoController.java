package ar.com.Practica2027.Hospital.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.Practica2027.Hospital.Entity.Producto;

@Controller
public class ProductoController {
	
	@GetMapping ("/producto/control")
	public String ControlP (Model model) {
		model.addAttribute("producto", new Producto());
		return "";
	}
}
