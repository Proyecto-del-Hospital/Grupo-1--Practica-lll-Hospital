package ar.com.Practica2027.Hospital.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.Practica2027.Hospital.Entity.Usuario;

@Controller
public class UsuarioController {
	
	
	@GetMapping("/AltaUsuario")
    public String formulario(Usuario usuario, Model model) {
		
        return "Usuario/RegistrarUsuario";
    }

    @PostMapping("/GuardarUsuario")
    public String guardar( Usuario usuario, Model model) {
    	
    	
            return "redirect:/";
    }
}
