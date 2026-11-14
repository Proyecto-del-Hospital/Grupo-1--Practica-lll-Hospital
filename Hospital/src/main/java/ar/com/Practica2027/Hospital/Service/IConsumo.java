package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Consumo;
import ar.com.Practica2027.Hospital.Entity.Det_Consumo;
import ar.com.Practica2027.Hospital.Entity.Empleado;

public interface IConsumo {
String registrarConsuDiario(List<Det_Consumo> detalles,Empleado empleadoLog,String turno);

Consumo BuscarPorID(int id);

List<Consumo>listaConsumo();
List<Det_Consumo>listaDetConsumo(int idConsumo);
}
