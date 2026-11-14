package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Entrega;

@Repository
public interface IEntregaRepository extends JpaRepository<Entrega, Integer>{
	Entrega findById (int id);
	List<Entrega> findByFecha (String fecha);
}
