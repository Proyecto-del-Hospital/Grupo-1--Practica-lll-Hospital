package ar.com.Practica2027.Hospital.Service;

import java.util.List;
import ar.com.Practica2027.Hospital.Entity.Paciente;

public interface IPaciente {
	
	String CrearPaciente (Paciente paciente);
	List<Paciente> ListarPacientes ();
	List<Paciente> buscarPaciente (String dni);
	String ModificarPaciente (Paciente paciente);
	Paciente BuscarPacienteXid (int id);
	void CambiarEstado (Paciente paciente);
}
