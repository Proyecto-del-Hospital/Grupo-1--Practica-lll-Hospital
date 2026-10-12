package ar.com.Practica2027.Hospital.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping ("/")
	public String MostrarInicio () {
		
		return "Inicio/Home";
	}
}
