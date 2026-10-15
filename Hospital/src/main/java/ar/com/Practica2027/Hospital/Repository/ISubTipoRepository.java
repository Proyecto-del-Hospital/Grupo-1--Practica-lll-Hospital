package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.SubTipo;

@Repository
public interface ISubTipoRepository extends JpaRepository<SubTipo, Integer>{

}
