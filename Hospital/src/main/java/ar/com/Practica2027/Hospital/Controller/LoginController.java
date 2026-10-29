package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Service.ILogin;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private ILogin loginService;
	
	@GetMapping ("/Inicio/Login")
	public String MostrarInicio () {
		
		return "Empleado/Login";
	}
	
	@PostMapping("/Login/Registrado")
	public String Menu(@RequestParam("nombreu") String nombreu, @RequestParam("contrasena") String contraseña, HttpSession session, Model model) {
		Empleado empleadoLogueado = loginService.autenticar(nombreu, contraseña);
		
		if (empleadoLogueado != null) {
			session.setAttribute("empleadologueado", empleadoLogueado);
			return "Inicio/Home";
			
			
		}else {
			model.addAttribute("error", "Usuario o Contraseña Incorrecta");
			return "Empleado/Login";
		}
	}
	
	@GetMapping("/Inicioprueba")
	public String mostrarinicio(HttpSession session) {
	if (session.getAttribute("empleadologueado") == null) {
		return "redirect:/Login";
	}
	return "Inicio/Home";
	
	}
}
