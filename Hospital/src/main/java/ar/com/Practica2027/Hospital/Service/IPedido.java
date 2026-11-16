package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Pedido;

public interface IPedido {
List<Pedido> listarPedido();
String GenerarPedido(int idSolicitud,String motivo,List<Integer> productosIds,List<Double> cantidades,Empleado empleadologueado);
}
