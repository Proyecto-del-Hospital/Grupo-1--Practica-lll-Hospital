package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.Practica2027.Hospital.Entity.Proveedor;
import ar.com.Practica2027.Hospital.Service.IProveedor;

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
	public String crearProveedor(Proveedor prov) {
		proveedorService.CrearProveedor(prov);
		return "redirect:/proveedor/control";
	}
	@PostMapping("/proveedor/buscar")
	public String buscarProveedor(String dni, Model model) {
		List<Proveedor> proveedores= proveedorService.BuscarProveedor(dni);
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("ListaProveedores", proveedores);
		
		return "Proveedor/ControlProveedor";
	}
    
}
