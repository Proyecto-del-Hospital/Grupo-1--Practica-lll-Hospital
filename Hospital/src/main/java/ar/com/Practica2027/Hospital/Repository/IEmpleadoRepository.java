package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

	Empleado findByid(int id);
	
	List<Empleado> findByDNI(String DNI);
}
