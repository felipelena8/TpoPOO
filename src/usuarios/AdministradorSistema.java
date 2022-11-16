package usuarios;

import enums.Rol;

public class AdministradorSistema extends Usuario {

	public AdministradorSistema(String nombre, String apellido, String usuario, String password) {
		super(nombre, apellido, usuario, password);
	}
	
	public Rol getRol() {
		return Rol.ADMINISTRADOR_SISTEMA;
	}
}
