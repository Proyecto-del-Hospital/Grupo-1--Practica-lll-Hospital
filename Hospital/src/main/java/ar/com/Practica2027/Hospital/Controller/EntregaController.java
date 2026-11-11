package ar.com.Practica2027.Hospital.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.Practica2027.Hospital.Entity.Det_Entrega;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Service.IEmpledo;
import ar.com.Practica2027.Hospital.Service.IEntrega;
import ar.com.Practica2027.Hospital.Service.IProducto;
import jakarta.servlet.http.HttpSession;

@Controller
public class EntregaController {

	@Autowired
	IProducto productoserv;

	@Autowired
	IEntrega entregaserv;

	@Autowired
	IEmpledo empledoserv;

	@GetMapping("/controlentrega")
	public String controlEntrega(Model model, HttpSession session) {

		List<Det_Entrega> detallesEntregas = (List<Det_Entrega>) session.getAttribute("ListaTemporalEntrega");

		if (detallesEntregas == null) {
			detallesEntregas = new ArrayList<>();
			session.setAttribute("ListaTemporalEntrega", detallesEntregas);
		}

		model.addAttribute("listaDetalles", detallesEntregas);
		model.addAttribute("ListaEmpleados", empledoserv.ListarEmpleados());
		model.addAttribute("ListaProductos", productoserv.ListarProductos());
		model.addAttribute("ListaEntregas", entregaserv.listarEntregas());

		return "Entrega/ControlEntrega";
	}

	@PostMapping("/Agregarproducto")
	public String AgregarProductos(@RequestParam("productoId") int productoId,
			@RequestParam("cantidad") double cantidad, HttpSession session) {
		List<Det_Entrega> detallesEntregas = (List<Det_Entrega>) session.getAttribute("ListaTemporalEntrega");
		Producto productoExistente = productoserv.buscarProductoPorId(productoId);
		Det_Entrega detalle = new Det_Entrega();
		detalle.setProducto(productoExistente);
		detalle.setCantidad(cantidad);
		detallesEntregas.add(detalle);

		return "redirect:/controlentrega";
	}

	@PostMapping("/Guardarentrega")
	public String GuardarEntrega(HttpSession session, @RequestParam("fecha") String fecha,
			@RequestParam("hora") String hora, @RequestParam("id_empleado") int id_empleado) {
		List<Det_Entrega> detallesEntregas = (List<Det_Entrega>) session.getAttribute("ListaTemporalEntrega");
		session.removeAttribute("ListaTemporalEntrega");
		Empleado empleadoExistente = empledoserv.buscarporid(id_empleado);
		entregaserv.CrearEntrega(detallesEntregas, empleadoExistente, fecha, hora);

		return "redirect:/controlentrega";
	}

	@PostMapping("/Quitarproductos")
	public String QuitarProductos(HttpSession session, @RequestParam("posicion") int posicion) {
		List<Det_Entrega> detallesEntregas = (List<Det_Entrega>) session.getAttribute("ListaTemporalEntrega");
		if (detallesEntregas != null && posicion >= 0) {
			for (int i = 0; i < detallesEntregas.size(); i++) {
				if (detallesEntregas.get(i).getProducto().getId() == posicion) {
					detallesEntregas.remove(i);
					break;
				}
			}
			session.setAttribute("ListaTemporalEntrega", detallesEntregas);
		}
		return "redirect:/controlentrega";
	}

	@GetMapping("/Limpiarproductos")
	public String limpiarProductos(HttpSession session) {
		List<Det_Entrega> detallesEntregas = (List<Det_Entrega>) session.getAttribute("ListaTemporalEntrega");
		session.removeAttribute("ListaTemporalEntrega");
		return "redirect:/controlentrega";
	}
	@GetMapping ("/MostrarDetalle")
	public String detalles (@RequestParam ("id") int id, Model model) {
		
		Entrega entrega = entregaserv.BuscarEntregaId(id);
		model.addAttribute("Entrega", entrega);
		
		return "Entrega/DetallesEntrega";
	}
}
