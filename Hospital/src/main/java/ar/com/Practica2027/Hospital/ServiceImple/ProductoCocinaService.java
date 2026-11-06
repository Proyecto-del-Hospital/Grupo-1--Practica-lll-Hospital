package ar.com.Practica2027.Hospital.ServiceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Producto;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Entity.SubTipo;
import ar.com.Practica2027.Hospital.Entity.Tipo;
import ar.com.Practica2027.Hospital.Entity.Unidad;
import ar.com.Practica2027.Hospital.Repository.IEstadoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoCocinaRepository;
import ar.com.Practica2027.Hospital.Repository.ISubTipoRepository;
import ar.com.Practica2027.Hospital.Repository.ITipoRepository;
import ar.com.Practica2027.Hospital.Repository.IUnidadRepository;
import ar.com.Practica2027.Hospital.Service.IProductoCocina;

@Service
public class ProductoCocinaService implements IProductoCocina {
	@Autowired
	private IProductoCocinaRepository productocRepo;

	@Autowired
	private ISubTipoRepository subtipoRepo;

	@Autowired
	private ITipoRepository tipoRepo;

	@Autowired
	private IUnidadRepository unidadRepo;

	@Autowired
	private IEstadoRepository estadoRepo;

	@Override
	public String crearProductoCocina(ProductoCocina productoC) {

		
		SubTipo subtipo = subtipoRepo.findBynombre(productoC.getSubtipo().getNombre());
		Unidad unidad = unidadRepo.findBynombre(productoC.getUnidad().getNombre());

		List<ProductoCocina> productosC = productocRepo.findAll();

		for (ProductoCocina p : productosC) {
			if (productoC.getNombre().equalsIgnoreCase(p.getNombre())) {
				return "El producto ya existe";
			}
		}

		if (productoC.getStockactual() <= productoC.getStockminimo()) {
			Estado estado = estadoRepo.findBynombre("Bajo Stock");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Bajo Stock");
				estadoRepo.save(estado);
			}
			productoC.setEstado(estado);
		} else {
			Estado estado = estadoRepo.findBynombre("Disponible");
			if (estado == null) {
				estado = new Estado();
				estado.setNombre("Disponible");
				estadoRepo.save(estado);
			}
			productoC.setEstado(estado);
		}
		if (unidad == null) {
			unidad = new Unidad();
			unidad.setNombre(productoC.getUnidad().getNombre());
			unidadRepo.save(unidad);
		}
		if (subtipo == null) {
			subtipo = new SubTipo();
			subtipo.setNombre(productoC.getSubtipo().getNombre());
			subtipoRepo.save(subtipo);
		}
		Tipo tipo = tipoRepo.findBynombre("Alimenticio");
		
		if (tipo == null) {
			tipo = new Tipo();
			tipo.setNombre("Alimenticio");
			tipoRepo.save(tipo);
		}
		productoC.setMotivo("-");
		productoC.setTipo(tipo);
		productoC.setSubtipo(subtipo);
		productoC.setUnidad(unidad);

		productocRepo.save(productoC);

		return "Producto creado correctamente";
	}

	@Override
	public List<ProductoCocina> ListarProductosC() {

		return productocRepo.findAll();
	}

	@Override
	public ProductoCocina buscarProductoC(String nombre) {

		return productocRepo.findBynombre(nombre);
	}

	@Override
	public String modificarProductoC(ProductoCocina productoC) {
		
		ProductoCocina productoExistente = productocRepo.findByid(productoC.getId());
		SubTipo subtipo = subtipoRepo.findBynombre(productoC.getSubtipo().getNombre());
		Unidad unidad = unidadRepo.findBynombre(productoC.getUnidad().getNombre());

		if (productoExistente != null) {

			productoExistente.setNombre(productoC.getNombre());
			productoExistente.setMotivo(productoC.getMotivo());
			productoExistente.setStockactual(productoC.getStockactual());
			productoExistente.setStockminimo(productoC.getStockminimo());
			
			if (subtipo == null) {
				subtipo = new SubTipo();
				subtipo.setNombre(productoC.getSubtipo().getNombre());
				subtipoRepo.save(subtipo);
			}
			if (unidad == null) {
				unidad = new Unidad();
				unidad.setNombre(productoC.getUnidad().getNombre());
				unidadRepo.save(unidad);
			}
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

				Estado estado = estadoRepo.findBynombre("Disponible");

				if (estado == null) {
					estado = new Estado();
					estado.setNombre("Disponible");
					estadoRepo.save(estado);
				}
				productoExistente.setEstado(estado);
			}
			productocRepo.save(productoExistente);

			return "Producto modificado con éxito";
		}
		return null;
	}

	@Override
	public ProductoCocina buscarProductocPorId(int id) {

		return productocRepo.findByid(id);
	}

	@Override
	public String CambiarEstado(ProductoCocina productoC) {
		ProductoCocina productoExistente = productocRepo.findByid(productoC.getId());

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
		productocRepo.save(productoExistente);

		return null;
	}

	@Override
	public List<ProductoCocina> BuscarEstado(String nombre) {
		 
		return productocRepo.findByEstadoNombre(nombre);
	}

}
