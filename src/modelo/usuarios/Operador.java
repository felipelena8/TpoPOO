package modelo.usuarios;

import modelo.enums.Rol;

public class Operador extends Usuario {

	public Operador(String nombre, String apellido, String usuario, String password) {
		super(nombre, apellido, usuario, password);
	}

	public Rol getRol() {
		return Rol.OPERADOR;
	}

}
