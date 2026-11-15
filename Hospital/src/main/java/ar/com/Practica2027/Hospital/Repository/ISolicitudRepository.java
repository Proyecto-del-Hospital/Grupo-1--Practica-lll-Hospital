package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.Solicitud;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud, Integer>{
	
	Solicitud findByid(int id);
	List<Solicitud> findAllByOrderByFechaDesc();
	
}
