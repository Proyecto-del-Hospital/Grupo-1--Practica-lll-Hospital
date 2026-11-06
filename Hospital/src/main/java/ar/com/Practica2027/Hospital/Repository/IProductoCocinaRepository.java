package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;

@Repository
public interface IProductoCocinaRepository extends JpaRepository<ProductoCocina, Integer>{
	ProductoCocina findBynombre (String nombre);
	ProductoCocina findByid (int id);
	List<ProductoCocina> findByEstadoNombre (String nombre);
}
