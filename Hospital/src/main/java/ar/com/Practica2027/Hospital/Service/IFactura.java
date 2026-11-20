package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Det_Factura;
import ar.com.Practica2027.Hospital.Entity.Proveedor;

public interface IFactura {
	String GuardarFactura(int idPedido, List<Integer> productosIds, List<Double> cantidades, String numFactura,
			Proveedor proveedor, List<Double> preciounitario, String motivo, String fecha);
	List<Det_Factura> listarDetFactura ();
}
