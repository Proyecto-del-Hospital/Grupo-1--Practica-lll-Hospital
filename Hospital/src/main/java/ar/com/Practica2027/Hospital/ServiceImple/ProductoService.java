package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.SubTipo;
import ar.com.Practica2027.Hospital.Entity.Tipo;
import ar.com.Practica2027.Hospital.Entity.Unidad;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoRepository;
import ar.com.Practica2027.Hospital.Repository.ISubTipoRepository;
import ar.com.Practica2027.Hospital.Repository.ITipoRepository;
import ar.com.Practica2027.Hospital.Repository.IUnidadRepository;
import ar.com.Practica2027.Hospital.Service.IProducto;

@Service
public class ProductoService implements IProducto{
	
	@Autowired
	private IProductoRepository productoRepo;
	
	@Autowired
	private ISubTipoRepository subtipoRepo;
	
	@Autowired
	private ITipoRepository tipoRepo;
	
	@Autowired
	private IUnidadRepository unidadRepo;
	
	@Autowired
	private IEstadoRepository estadoRepo;

	@Override
	public String CrearProducto(Producto producto) {
		
		Tipo tipo = tipoRepo.findBynombre(producto.getTipo().getNombre());
		SubTipo subtipo = subtipoRepo.findBynombre(producto.getSubtipo().getNombre());
		Unidad unidad = unidadRepo.findBynombre(producto.getUnidad().getNombre());
		Estado estado = estadoRepo.findBynombre("Disponible");
		
		List<Producto> productos = productoRepo.findAll();
		
		for (Producto p : productos) {
			if (producto.getNombre().equalsIgnoreCase(p.getNombre())) {
				return "El producto ya existe";
			}
		}
		if (estado == null) {
			estado = new Estado();
			estado.setNombre("Disponible");
			estadoRepo.save(estado);
		}
		if (unidad == null) {
			unidad = new Unidad();
			unidad.setNombre(producto.getUnidad().getNombre());
			unidadRepo.save(unidad);
		}
		if (subtipo == null) {
			subtipo = new SubTipo();
			subtipo.setNombre(producto.getSubtipo().getNombre());
			subtipoRepo.save(subtipo);
		}
		if (tipo == null) {
			tipo = new Tipo();
			tipo.setNombre(producto.getTipo().getNombre());
			tipoRepo.save(tipo);
		}
		producto.setMotivo("-");
		producto.setTipo(tipo);
		producto.setSubtipo(subtipo);
		producto.setUnidad(unidad);
		producto.setEstado(estado);
		
		productoRepo.save(producto);
		
		return "Producto creado correctamente";
	}

	@Override
	public List<Producto> ListarProductos() {
		
		return productoRepo.findAll();
	}

	@Override
	public Producto buscarProducto(String nombre) {
		
		return productoRepo.findBynombre(nombre);
	}

}
