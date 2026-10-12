package ar.com.Practica2027.Hospital.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.Practica2027.Hospital.Entity.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer>{
	List<Paciente> findByDni(String dni);
	Paciente findByid (int id);
}
