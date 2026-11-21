package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.Practica2027.Hospital.Entity.Factura;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Integer>{
	Factura findById (int id);
	Factura findBynumfactura (String numfactura);
}
