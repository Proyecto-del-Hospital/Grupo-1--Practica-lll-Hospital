package ar.com.Practica2027.Hospital.ServiceImple;

import java.time.LocalDate;
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
	    for (Det_Consumo det_Consumo : detalles) {
	    	ProductoCocina productoC = productoCRepo.findByid(det_Consumo.getProductococina().getId());
	    	if(productoC==null) {
	    		return "Un producto seleccionado ya no existe en la base de datos."+ det_Consumo.getProductococina().getNombre();
	    	}
		}
	 
	    
	    String fechaHoy = LocalDate.now().toString();
	    Consumo consumo = new Consumo();
	    consumo.setFecha(fechaHoy);

	    consumo = consumoRepo.save(consumo);
	    Turno turnohoy = turnoRepo.findByNombre(turno);
		if (turnohoy == null) {
			turnohoy = new Turno();
			turnohoy.setNombre(turno);
			turnoRepo.save(turnohoy);
		}
	   
	    for (Det_Consumo det : detalles) {
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


}
