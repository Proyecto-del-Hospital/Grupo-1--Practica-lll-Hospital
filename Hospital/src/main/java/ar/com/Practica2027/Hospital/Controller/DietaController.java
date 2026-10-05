package ar.com.Practica2027.Hospital.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Dieta;
import ar.com.Practica2027.Hospital.Entity.Paciente;
import ar.com.Practica2027.Hospital.Entity.Patologia;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IPacienteRepository;
import ar.com.Practica2027.Hospital.Repository.IPatologiaRepository;
import ar.com.Practica2027.Hospital.Repository.ISalaRepository;
import ar.com.Practica2027.Hospital.Service.IDietaService;

@Controller
public class DietaController {
	@Autowired
    private IDietaService DietaService;


    @PostMapping ("dieta/crear")
	public String GuardarDieta(Model model,Dieta dieta, @RequestParam List<Integer> patologiasIds,@RequestParam String SalaNueva,@RequestParam Integer SalaId) {
		String mensaje = DietaService.CrearDieta(dieta,patologiasIds,SalaNueva,SalaId);
		model.addAttribute("mensaje", mensaje);
		return "layout/home";
	}
    
    @GetMapping("dieta/formulario")
    public String FormularioDieta(Model model) {
    	
    	model.addAttribute("dieta", new Dieta());
        model.addAttribute("listaPacientes", DietaService.ListarPacientes());
        model.addAttribute("listaPatologias", DietaService.ListarPatologias());
        model.addAttribute("listaSalas",DietaService.ListarSala());
   
    	return "Dieta/RegistrarDieta"; 
    }
    
    @GetMapping("patologia/formulario")
    public String FormularioPatologia(Model model) {
    	model.addAttribute("patologia", new Patologia());
    	return "Dieta/RegistrarPatologia";
    }
    
    @PostMapping ("patologia/crear")
	public String GuardarPatologia(Patologia patologia) {
		DietaService.CrearPatologia(patologia);
		return "redirect:/";
	}	
    }

