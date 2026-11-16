package ar.com.Practica2027.Hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{
Pedido findByid(int id);
}
