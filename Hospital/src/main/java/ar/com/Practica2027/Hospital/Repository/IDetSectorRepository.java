package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.Practica2027.Hospital.Entity.Det_Sector;

public interface IDetSectorRepository extends JpaRepository<Det_Sector, Integer> {

	List<Det_Sector> findBySectorId(int id);
	
}
