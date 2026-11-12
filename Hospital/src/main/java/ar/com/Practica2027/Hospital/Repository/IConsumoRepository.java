package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Consumo;

@Repository
public interface IConsumoRepository extends JpaRepository<Consumo, Integer> {
	Consumo findByid(int id);
}
