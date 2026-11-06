package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Service.IProductoCocina;

@Controller
public class ProductoCocinaController {

	@Autowired
	IProductoCocina productocService;

	@GetMapping("/controlproductoC")
	public String ControlProductoC(Model model) {
		model.addAttribute("productoC", new ProductoCocina());
		model.addAttribute("ListaProductosC", productocService.ListarProductosC());
		return "ProductoCocina/ControlProductoCocina";
	}

	@PostMapping("/guardarproductoC")
	public String guardarProductoc(ProductoCocina productoC, RedirectAttributes redirect) {
		redirect.addFlashAttribute("exito", productocService.crearProductoCocina(productoC));
		return "redirect:/controlproductoC";
	}

	@PostMapping("/busquedaProductoC")
	public String buscarproducto(Model model, String nombre) {
		ProductoCocina p = productocService.buscarProductoC(nombre);
		model.addAttribute("productoC", new ProductoCocina());
		model.addAttribute("ListaProductos", p);
		return "ProductoCocina/ControlProductoCocina";
	}

	@GetMapping("/productoC/formularioM")
	public String FormularioModificar(@RequestParam("id") int id, Model model) {
		ProductoCocina producto = productocService.buscarProductocPorId(id);
		model.addAttribute("productoC", producto);
		model.addAttribute("Editar", "Editando");
		model.addAttribute("ListaProductosC", productocService.ListarProductosC());
		return "ProductoCocina/ControlProductoCocina";
	}

	@PostMapping("/modificarproductoC")
	public String ModificarProducto(ProductoCocina productoC, RedirectAttributes redirect) {

		redirect.addFlashAttribute("exito", productocService.modificarProductoC(productoC));

		return "redirect:/controlproductoC";
	}

	@GetMapping("/productoC/cambiarEstado")
	public String CambiarEstado(ProductoCocina productoC) {
		productocService.CambiarEstado(productoC);
		return "redirect:/controlproductoC";
	}

	@GetMapping("/productoC/filtrar")
	public String filtrarProducto(Model model, String nombre) {
		List<ProductoCocina> ListarProductos = productocService.BuscarEstado(nombre);
		model.addAttribute("productoC", new ProductoCocina());
		model.addAttribute("ListaProductosC", ListarProductos);
		return "ProductoCocina/ControlProductoCocina";
	}
}
