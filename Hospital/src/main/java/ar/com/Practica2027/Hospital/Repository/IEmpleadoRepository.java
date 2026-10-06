package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.Practica2027.Hospital.Entity.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

	
}
