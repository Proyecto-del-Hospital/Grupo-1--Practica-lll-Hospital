package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Consumo;
import ar.com.Practica2027.Hospital.Entity.Det_Consumo;
import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Estado;
import ar.com.Practica2027.Hospital.Entity.ProductoCocina;
import ar.com.Practica2027.Hospital.Entity.Turno;
import ar.com.Practica2027.Hospital.Repository.IConsumoRepository;
import ar.com.Practica2027.Hospital.Repository.IDetConsumoRepository;
import ar.com.Practica2027.Hospital.Repository.IProductoCocinaRepository;
import ar.com.Practica2027.Hospital.Repository.ITurnoRepository;
import ar.com.Practica2027.Hospital.Service.IConsumo;
import ar.com.Practica2027.Hospital.Service.IProductoCocina;

@Service
public class ConsumoService implements IConsumo {

	@Autowired
	IConsumoRepository consumoRepo;
	@Autowired
	IDetConsumoRepository detConsumoRepo;
	@Autowired
	ITurnoRepository turnoRepo;
	@Autowired
	IProductoCocinaRepository productoCRepo;
	@Override
	public String registrarConsuDiario(List<Det_Consumo> detalles, Empleado empleadoLog, String turno) {
		if (detalles == null || detalles.isEmpty()) {
			return "No hay productos en la Lista";
		}
		
		List<Det_Consumo> listaSinRepetir = new ArrayList<>();
	    for (Det_Consumo det : detalles) {
	        boolean encontrado = false;
	        for (Det_Consumo s: listaSinRepetir) {
	            if (s.getProductococina().getId() == det.getProductococina().getId()) {
	                s.setCantidad(s.getCantidad() + det.getCantidad());
	                encontrado = true;
	            }
	        }
	        if (!encontrado) {
	            listaSinRepetir.add(det);
	        }  }
	    
	    for (Det_Consumo det_Consumo : listaSinRepetir) {
	        ProductoCocina productoC = productoCRepo.findByid(det_Consumo.getProductococina().getId());
	        if (det_Consumo.getCantidad() > productoC.getStockactual()) {
	            return "Stock insuficiente para " + productoC.getNombre() + " (Disponible: " + productoC.getStockactual() + ")";
	        } }
	    
	    String fechaHoy = LocalDate.now().toString();
	    Consumo consumo = consumoRepo.findByFecha(fechaHoy);
	    if (consumo == null) {
	        consumo = new Consumo();
	        consumo.setFecha(fechaHoy);
	        consumo = consumoRepo.save(consumo);
	    }
	    Turno turnohoy = turnoRepo.findByNombre(turno);
		if (turnohoy == null) {
			turnohoy = new Turno();
			turnohoy.setNombre(turno);
			turnoRepo.save(turnohoy);
		}
	   
	    for (Det_Consumo det : listaSinRepetir) {
	    	ProductoCocina productoC = productoCRepo.findByid(det.getProductococina().getId());
	    	double nuevoStock = productoC.getStockactual() - det.getCantidad();
	    	productoC.setStockactual(nuevoStock);
	    	productoCRepo.save(productoC);
	    	
	        det.setConsumo(consumo);
	        det.setEmpleado(empleadoLog);
	        det.setTurno(turnohoy);
	        
	        detConsumoRepo.save(det);
	    }

	    return "Consumo Diario Guardado con Éxito";
		
	}
	@Override
	public Consumo BuscarPorID(int id) {
		return consumoRepo.findByid(id);
	}
	@Override
	public List<Consumo> listaConsumo() {
		return consumoRepo.findAllByOrderByFechaDesc();
	}
	@Override
	public List<Det_Consumo> listaDetConsumo(int idConsumo) {
		
		return detConsumoRepo.findByConsumoId(idConsumo);
	}

}
