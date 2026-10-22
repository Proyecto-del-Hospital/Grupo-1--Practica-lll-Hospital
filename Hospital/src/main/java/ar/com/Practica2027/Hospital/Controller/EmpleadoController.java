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
import ar.com.Practica2027.Hospital.Entity.Sector;
import ar.com.Practica2027.Hospital.ServiceImple.EmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleService;
      
	@GetMapping("/Alta/Empleado")
	public String RegistrarEmpleado(Model model) {
		  
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("ListaSectores", empleService.listarSector());
		
		return "Empleado/RegistrarEmpleado";
	}
	
	@PostMapping("/GuardarEmpleado")
    public String guardar(Empleado empleado, @RequestParam(name="sectorids",required = false) List<Integer> sectorids, RedirectAttributes redired) {
    	
    	
    	redired.addFlashAttribute("mensaje", empleService.CrearEmpleado(empleado, sectorids));
    	
     return "redirect:/Alta/Empleado";
    }
	
	@GetMapping("/formulario/sector")
	public String formulariosector (Model model) {
		
		model.addAttribute("sector", new Sector());
		
		return "/Empleado/RegistrarSector";
	}
	
	@PostMapping("/guardar/sector")
	public String guardarsector(Sector sector) {
		empleService.guardarsector(sector);
	return "redirect:/Alta/Empleado";
	}
	
	
	
	
}
