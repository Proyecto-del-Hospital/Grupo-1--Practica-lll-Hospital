package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	Producto findBynombre (String nombre);
	Producto findByid (int id);
	List<Producto> findByEstadoNombre (String nombre);
}
