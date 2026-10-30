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
public class ProductoService implements IProducto {

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

		List<Producto> productos = productoRepo.findAll();

		for (Producto p : productos) {
			if (producto.getNombre().equalsIgnoreCase(p.getNombre())) {
				return "El producto ya existe";
			}
		}

		if (producto.getStockactual() <= producto.getStockminimo()) {
			Estado estado = estadoRepo.findBynombre("Bajo Stock");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Bajo Stock");
				estadoRepo.save(estado);
			}
			producto.setEstado(estado);
		} else {
			Estado estado = estadoRepo.findBynombre("Disponible");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Disponible");
				estadoRepo.save(estado);
			}
			producto.setEstado(estado);
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

	@Override
	public String ModificarProducto(Producto producto) {

		Producto productoExistente = productoRepo.findByid(producto.getId());
		Tipo tipo = tipoRepo.findBynombre(producto.getTipo().getNombre());
		SubTipo subtipo = subtipoRepo.findBynombre(producto.getSubtipo().getNombre());
		Unidad unidad = unidadRepo.findBynombre(producto.getUnidad().getNombre());

		if (productoExistente != null) {

			productoExistente.setNombre(producto.getNombre());
			productoExistente.setMotivo("-");
			productoExistente.setStockactual(producto.getStockactual());
			productoExistente.setStockminimo(producto.getStockminimo());

			if (tipo == null) {
				tipo = new Tipo();
				tipo.setNombre(producto.getTipo().getNombre());
				tipoRepo.save(tipo);
			}
			if (subtipo == null) {
				subtipo = new SubTipo();
				subtipo.setNombre(producto.getSubtipo().getNombre());
				subtipoRepo.save(subtipo);
			}
			if (unidad == null) {
				unidad = new Unidad();
				unidad.setNombre(producto.getUnidad().getNombre());
				unidadRepo.save(unidad);
			}
			productoExistente.setTipo(tipo);
			productoExistente.setSubtipo(subtipo);
			productoExistente.setUnidad(unidad);

			if (productoExistente.getStockactual() <= productoExistente.getStockminimo()) {

				Estado estado = estadoRepo.findBynombre("Bajo Stock");

				if (estado == null) {
					estado = new Estado();
					estado.setNombre("Bajo Stock");
					estadoRepo.save(estado);
				}
				productoExistente.setEstado(estado);
			} else {

				Estado estado = estadoRepo.findBynombre(producto.getEstado().getNombre());

				if (estado == null) {
					estado = new Estado();
					estado.setNombre(producto.getEstado().getNombre());
					estadoRepo.save(estado);
				}
				productoExistente.setEstado(estado);
			}
			productoRepo.save(productoExistente);

			return "Producto modificado con éxito";
		}
		return null;
	}

	@Override
	public Producto buscarProductoPorId(int id) {

		return productoRepo.findByid(id);
	}

	@Override

	public String CambiarEstado(Producto producto) {

		Producto productoExistente = productoRepo.findByid(producto.getId());

		if (productoExistente.getEstado().getNombre().equals("Disponible")
				|| productoExistente.getEstado().getNombre().equals("Bajo Stock")) {

			Estado estado = estadoRepo.findBynombre("Deshabilitado");

			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Deshabilitado");
				estadoRepo.save(estado);
			}

			productoExistente.setEstado(estado);

		} else if (productoExistente.getStockactual() <= productoExistente.getStockminimo()) {

			Estado estado = estadoRepo.findBynombre("Bajo Stock");

			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Bajo Stock");
				estadoRepo.save(estado);
			}

			productoExistente.setEstado(estado);

		} else {

			Estado estado = estadoRepo.findBynombre("Disponible");

			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Disponible");
				estadoRepo.save(estado);
			}

			productoExistente.setEstado(estado);
		}
		// feliz navidad :)
		productoRepo.save(productoExistente);

		return null;
	}

	@Override
	public List<Producto> BuscarEstado(String nombre) {
		
		return productoRepo.findByEstadoNombre(nombre);
	}


}
