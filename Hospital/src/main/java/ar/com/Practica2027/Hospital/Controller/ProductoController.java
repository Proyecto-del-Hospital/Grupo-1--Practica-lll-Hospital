package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		model.addAttribute("ListaProductos", productoserv.ListarProductos());
		return "Producto/ControlProducto";
	}
	@PostMapping ("/guardarproducto")
	public String GuardarProducto (Producto producto,RedirectAttributes redirect) {
		
		redirect.addFlashAttribute("exito", productoserv.CrearProducto(producto));
		
		return "redirect:/controlproducto";
	}
	@PostMapping ("/busquedaProducto")
	public String buscarproducto (Model model, String nombre) {
		Producto p = productoserv.buscarProducto(nombre);
		model.addAttribute("producto", new Producto());
		model.addAttribute("ListaProductos", p);
		return "Producto/ControlProducto";
	}
	@GetMapping ("/producto/formularioM")
	public String FormularioModificar (@RequestParam("id") int id, Model model) {
		Producto producto = productoserv.buscarProductoPorId(id);
		model.addAttribute("producto", producto);
		model.addAttribute("Editar", "Editando");
		model.addAttribute("ListaProductos", productoserv.ListarProductos());
		return "Producto/ControlProducto";
	}
	@PostMapping ("/modificarproducto")
	public String ModificarProducto (Producto producto, RedirectAttributes redirect) {
		
		redirect.addFlashAttribute("exito", productoserv.ModificarProducto(producto));
		
		return "redirect:/controlproducto";
	}
}
