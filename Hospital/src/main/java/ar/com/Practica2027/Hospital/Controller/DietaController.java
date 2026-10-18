package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String GuardarDieta(Model model, Dieta dieta,@RequestParam(name = "patologiasIds", required = false) List<Integer> patologiasIds,
			@RequestParam(required = false) String SalaNueva, @RequestParam(required = false) Integer SalaId, RedirectAttributes redirect) {
		String mensaje = dietaService.CrearDieta(dieta, patologiasIds, SalaNueva, SalaId);
		
		redirect.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/dieta/ControlDieta";
	}
 
	@GetMapping("/dieta/ControlDieta")
	public String ControlDieta(Model model) {
		model.addAttribute("dieta", new Dieta());
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		model.addAttribute("listaDietas", dietaService.ListarDieta());
		return "Dieta/ControlDieta";
	}

	@PostMapping("/dieta/buscar")
	public String BuscarPaciente(String dni, Model model) {
		List<Dieta> listarDietas = dietaService.BuscarDietaPorDni(dni);
		model.addAttribute("dieta", new Dieta());
		model.addAttribute("listaDietas", listarDietas);
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		return "Dieta/ControlDieta";
	}

	@GetMapping("/dieta/editar")
	public String FormularioModificarDieta(@RequestParam("id") int id, Model model) {
		Dieta dieta = dietaService.BuscarDietaXid(id); 
		model.addAttribute("dieta", dieta);
		model.addAttribute("Editar", "Editando");
		
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		model.addAttribute("listaDietas", dietaService.ListarDieta());
		
		return "Dieta/ControlDieta"; 
	}

	@PostMapping("/dieta/editar/crear")
	public String ModificarDieta(Model model,Dieta dieta, 
			@RequestParam(required = false) List<Integer> patologiasIds,
			@RequestParam(required = false) String SalaNueva, 
			@RequestParam(required = false) Integer SalaId,RedirectAttributes redirect) {
		
		String mensaje=dietaService.ModificarDieta(dieta, patologiasIds, SalaNueva, SalaId);
		redirect.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/dieta/ControlDieta"; 
	}
	
	@GetMapping("patologia/formulario")
	public String FormularioPatologia(Model model) {
		model.addAttribute("patologia", new Patologia());
		return "Dieta/RegistrarPatologia";
	}

	@PostMapping("patologia/crear")
	public String GuardarPatologia(Patologia patologia) {
		dietaService.CrearPatologia(patologia);
		return "redirect:/patologia/formulario";
	}
	
	@GetMapping("/dieta/filtrar")
	public String FiltrarDieta(Model model,@RequestParam String estado) {
		List<Dieta> listarDietas = dietaService.BuscarPorEstado(estado);
		model.addAttribute("dieta", new Dieta());
		model.addAttribute("listaDietas", listarDietas);
		model.addAttribute("ListaPacientes", pacienteService.ListarPacientes());
		model.addAttribute("listaPatologias", dietaService.ListarPatologias());
		model.addAttribute("listaSalas", dietaService.ListarSala());
		return "Dieta/ControlDieta";
	}
	
	@GetMapping("/movimientos/dieta")
	public String ListaMovimentoDieta(@RequestParam("id") int id, Model model) {
		Dieta MiDieta = dietaService.BuscarDietaXid(id);
		model.addAttribute("dieta", MiDieta);
		model.addAttribute("listaDietas", dietaService.ListarDieta());
		model.addAttribute("ListaMovimientos", dietaService.ListarMovimientoDietas(id));
		return "Dieta/DetallesDietas";
	}
	
	@GetMapping("/dieta/cambiarEstado")
	public String CambiarEstadoDieta(Dieta dieta) {
		dietaService.CambiarEstado(dieta);
	return "redirect:/dieta/ControlDieta";
	}
	
	}

