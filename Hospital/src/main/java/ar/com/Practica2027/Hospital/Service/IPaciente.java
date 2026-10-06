package ar.com.Practica2027.Hospital.Service;

import java.util.List;

import ar.com.Practica2027.Hospital.Entity.Paciente;

public interface IPaciente {
	
	void CrearPaciente (Paciente paciente);
	List<Paciente> ListarPacientes ();
	void ModificarPaciente (Paciente paciente);
	Paciente BuscarPaciente (Long id);
	void CambiarEstado (Paciente paciente);
}
