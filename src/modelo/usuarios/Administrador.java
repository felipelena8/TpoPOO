package modelo.usuarios;

import modelo.enums.Rol;

public class Administrador extends Usuario {

	public Administrador(String nombre, String apellido, String usuario, String password) {
		super(nombre, apellido, usuario, password);
	}

	public Rol getRol() {
		return Rol.ADMINISTRADOR;
	}

}
