package ar.com.Practica2027.Hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Service.IPaciente;

@Controller
public class PacienteController {
	
	@Autowired
	private IPaciente pacienteServ;
	
	@PostMapping ("/Pacientes/Crear")
	public String GuardarPaciente (Paciente paciente) {
		pacienteServ.CrearPaciente(paciente);
		return "redirect:/";
	}
	
	@GetMapping ("/paciente/formulario")
	public String FormularioPaciente (Model model) {
		
		model.addAttribute("paciente", new Paciente());
		
		return "Pacientes/RegistrarPaciente";
	}
	
}
