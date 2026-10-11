package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Direccion;

@Repository
public interface IDireccionRepository extends JpaRepository<Direccion, Integer>{

}
