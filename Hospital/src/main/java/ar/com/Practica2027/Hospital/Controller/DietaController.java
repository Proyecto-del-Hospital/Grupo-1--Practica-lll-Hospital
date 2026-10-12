package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Service.IDieta;
import ar.com.Practica2027.Hospital.Service.IPaciente;

@Controller
public class DietaController {

	@Autowired
	private IDieta dietaService;
	
	@Autowired
	private IPaciente pacienteService;
	
	@PostMapping("/dieta/crear")
	public String GuardarDieta(Model model,Dieta dieta,@RequestParam List<Integer> patologiasIds,@RequestParam(required= false) String SalaNueva,
			@RequestParam(required= false) Integer SalaId) {
		String mensaje = dietaService.CrearDieta(dieta, patologiasIds, SalaNueva, SalaId);
		
		model.addAttribute("dieta", new Dieta());
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		
		return "Dieta/ControlDieta";
	}
	
	@GetMapping("/dieta/ControlDieta")
	public String ControlDieta(Model model) {
		model.addAttribute("dieta", new Dieta());
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		return "Dieta/ControlDieta";
	}
	
	@GetMapping("patologia/formulario")
	public String FormularioPatologia(Model model) {
		
		model.addAttribute("patologia", new Patologia());
		return "Dieta/RegistrarPatologia";
	}

	@PostMapping("patologia/crear")
	public String GuardarPatologia(Patologia patologia) {
		
		dietaService.CrearPatologia(patologia);
		return "redirect:/	patologia/formulario";
	}
}
