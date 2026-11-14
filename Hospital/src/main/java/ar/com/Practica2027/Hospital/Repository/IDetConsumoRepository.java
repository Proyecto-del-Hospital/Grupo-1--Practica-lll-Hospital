package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Det_Consumo;

@Repository
public interface IDetConsumoRepository extends JpaRepository<Det_Consumo, Integer>{
	List<Det_Consumo> findByConsumoId(int idConsumo);
}
