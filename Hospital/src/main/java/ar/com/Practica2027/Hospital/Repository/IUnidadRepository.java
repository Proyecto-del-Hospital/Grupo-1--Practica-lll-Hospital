package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Unidad;

@Repository
public interface IUnidadRepository extends JpaRepository<Unidad, Integer>{
	Unidad findBynombre (String nombre);
}
