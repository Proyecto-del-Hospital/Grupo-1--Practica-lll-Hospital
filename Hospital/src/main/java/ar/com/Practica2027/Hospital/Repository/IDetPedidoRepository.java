package ar.com.Practica2027.Hospital.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.Practica2027.Hospital.Entity.Det_Pedido;

@Repository
public interface IDetPedidoRepository extends JpaRepository<Det_Pedido, Integer>{
List<Det_Pedido> findByPedidoId(int id);
}
