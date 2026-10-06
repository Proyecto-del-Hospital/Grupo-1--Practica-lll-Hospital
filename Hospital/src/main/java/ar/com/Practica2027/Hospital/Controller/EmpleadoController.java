package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Rol;
import ar.com.Practica2027.Hospital.Service.IEmpleadoService;

@Controller
public class EmpleadoController {
    
	@Autowired
	private IEmpleadoService empleService;
	
	    @GetMapping("/AltaEmpleado")
	    public String RegistrarEmpleado(Model model) {
	    	
	    	model.addAttribute("empleado", new Empleado());
	    	model.addAttribute("listaroles", empleService.ListaRoles());
	        return "Empleado/RegistrarEmpleado";
	    }

	    @PostMapping("/GuardarEmpleado")
	    public String guardar(Model model, Empleado empleado) {
	      
	    	empleService.CrearEmpleado(empleado);
	    	
	            return "redirect:/";
	    }
	    @GetMapping("/AltaRol")
	    public String formulario(Rol rol, Model model) {
	        model.addAttribute("rol", new Rol());
	        
	        return "/Rol/RegistrarRol";
	    }

	    @PostMapping("/GuardarRol")
	    public String GuardarRol(Rol rol, Model model) {
	    	empleService.CrearRol(rol);
	    	return "redirect:/";
	    }
	    
}
