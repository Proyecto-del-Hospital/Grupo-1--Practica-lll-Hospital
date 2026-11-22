package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Service.IEmpleado;
import ar.com.Practica2027.Hospital.Service.ILogin;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	ILogin loginService;
	
	@Autowired
	IEmpleado empleadoservice;
	
	@GetMapping ("/Inicio/Login")
	public String MostrarInicio (HttpSession session) {
		
		empleadoservice.inicializardatos();
		session.removeAttribute("empleadologueado");
		
		return "Empleado/Login";
	}
	
	@PostMapping("/Login/Registrado")
	public String Menu(@RequestParam("nombreu") String nombreu, @RequestParam("contrasena") String contraseña, HttpSession session, Model model) {
		Empleado empleadoLogueado = loginService.autenticar(nombreu, contraseña);
		
		if (empleadoLogueado != null) {
			session.setAttribute("empleadologueado", empleadoLogueado);
			return "Inicio/Menu";
				
		}else {
			model.addAttribute("error", "Datos no Encontrados");
			return "Empleado/Login";
		}
	}

}
