package ar.com.Practica2027.Hospital.ServiceImple;

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
	public void CrearProducto(Producto producto) {
		
		Tipo tipo = tipoRepo.save(producto.getTipo());
		SubTipo subtipo = subtipoRepo.save(producto.getSubtipo());
		Unidad unidad = unidadRepo.save(producto.getUnidad());
		Estado estado = estadoRepo.findBynombre("Disponible");
		
		if (estado == null) {
			estado = new Estado();
			estado.setNombre("Disponible");
			estadoRepo.save(estado);
		}
		
		producto.setTipo(tipo);
		producto.setSubtipo(subtipo);
		producto.setUnidad(unidad);
		producto.setEstado(estado);
		
		productoRepo.save(producto);
		
	}

}
