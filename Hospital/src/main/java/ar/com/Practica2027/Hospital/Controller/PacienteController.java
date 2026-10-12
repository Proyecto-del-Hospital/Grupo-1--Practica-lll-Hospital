package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Service.IPaciente;

@Controller
public class PacienteController {
	
	@Autowired
	private IPaciente pacienteServ;
	
	@PostMapping ("/Pacientes/Crear")
	public String GuardarPaciente (Paciente paciente) {
		pacienteServ.CrearPaciente(paciente);
		return "redirect:/paciente/formulario";
	}
	
	@GetMapping ("/paciente/formulario")
	public String FormularioPaciente (Model model) {
		
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("ListaPacientes", pacienteServ.ListarPacientes());
		
		return "Paciente/ControlPaciente";
	}
	@PostMapping ("/paciente/buscar")
	public String BuscarPaciente (String dni, Model model) {
		List<Paciente> p = pacienteServ.buscarPaciente(dni);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("ListaPacientes", p);
		return "Paciente/ControlPaciente";
	}
	@GetMapping ("/FormularioModificar")
	public String FormularioModificar ( @RequestParam("id") int id , Model model) {
		Paciente paciente = pacienteServ.BuscarPacienteXid(id);
		model.addAttribute("paciente", paciente);
		return "Paciente/ModificarPaciente";
	}
	@PostMapping ("/modificarpaciente")
	public String ModificarPaciente (Paciente paciente) {
		pacienteServ.ModificarPaciente(paciente);
		return "redirect:/paciente/formulario";
	}
	@GetMapping ("/paciente/cambiarEstado")
	public String CambiarEstado (Paciente paciente) {
		pacienteServ.CambiarEstado(paciente);
		return "redirect:/paciente/formulario";
	}
}
