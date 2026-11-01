package ar.com.Practica2027.Hospital.Service;

import ar.com.Practica2027.Hospital.Entity.Empleado;

public interface ILogin {
	Empleado autenticar(String nombreu, String contraseña);
}
