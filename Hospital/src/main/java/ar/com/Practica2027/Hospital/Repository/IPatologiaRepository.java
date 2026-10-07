package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Patologia;

@Repository
public interface IPatologiaRepository extends JpaRepository<Patologia, Integer> {
Patologia findByid(int id);
}
