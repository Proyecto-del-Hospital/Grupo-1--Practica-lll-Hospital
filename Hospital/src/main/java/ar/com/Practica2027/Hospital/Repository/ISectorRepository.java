package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Sector;
@Repository
public interface ISectorRepository extends JpaRepository<Sector, Integer>{

	Sector findByid(int id);
}
