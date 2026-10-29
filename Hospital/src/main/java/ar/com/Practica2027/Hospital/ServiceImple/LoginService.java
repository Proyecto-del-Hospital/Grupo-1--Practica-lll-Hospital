package ar.com.Practica2027.Hospital.ServiceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.Practica2027.Hospital.Entity.Empleado;
import ar.com.Practica2027.Hospital.Entity.Usuario;
import ar.com.Practica2027.Hospital.Repository.ILoginRepository;
import ar.com.Practica2027.Hospital.Repository.IUsuarioRepository;
import ar.com.Practica2027.Hospital.Service.ILogin;
import org.mindrot.jbcrypt.BCrypt;
@Service
public class LoginService implements ILogin{

	
	@Autowired
	private ILoginRepository loginrepo;
	
	@Autowired
	private IUsuarioRepository usuarirepo;
	
	@Override
	public Empleado autenticar(String nombreu, String contraseña) {
			Usuario loginempleado = loginrepo.findByNombreu(nombreu);
		
			if (loginempleado.getNombreu().equalsIgnoreCase(nombreu)) {
				
				if (BCrypt.checkpw(contraseña, loginempleado.getContrasena())) {
					return loginempleado.getEmpleado();
				}
			}
		return null;
	}

}
