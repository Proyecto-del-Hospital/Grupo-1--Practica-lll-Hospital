package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
 Turno findByNombre(String nombre);
}
