package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.Practica2027.Hospital.Entity.Proveedor;
import ar.com.Practica2027.Hospital.Service.IProveedor;
import ar.com.Practica2027.Hospital.ServiceImple.ProveedorService;

@Controller

public class ProveedorController {
	@Autowired
	private IProveedor proveedorService;

	@GetMapping("/proveedor/control")
	public String controlProveedor(Model model) {

		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("ListaProveedores", proveedorService.ListarProveedores());
		return "Proveedor/ControlProveedor";
	}

	@PostMapping("/proveedor/crear")
	public String crearProveedor(Proveedor prov,RedirectAttributes redirect) {
		String mensaje= proveedorService.CrearProveedor(prov);
		redirect.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/proveedor/control";
	}
	@PostMapping("/proveedor/buscar")
	public String buscarProveedor(String dni, Model model) {
		List<Proveedor> proveedores= proveedorService.BuscarProveedor(dni);
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("ListaProveedores", proveedores);
		
		return "Proveedor/ControlProveedor";
	}
	@GetMapping("/Formulario/Modificar/proveedor")
	public String FormularioModificar(int id, Model model) {
		Proveedor proveedor = proveedorService.BuscarProveedor(id);
		model.addAttribute("proveedor", proveedor);
		return "Proveedor/ModificarProveedor";
	}
	@PostMapping("/modificar/Proveedor")
		public String modificarProveedor(Proveedor proveedor,RedirectAttributes redirect) {
			String mensaje= proveedorService.ModificarProveedor(proveedor);
			redirect.addFlashAttribute("mensaje", mensaje);
			return "redirect:/proveedor/control";
		}
	@GetMapping("/proveedor/cambiarEstado")
	public String cambiarEstadoProveedor (Proveedor proveedor) {
		proveedorService.cambiarEstadoProveedor(proveedor);
		
		return "redirect:/proveedor/control";
		 
	}
	}
	
