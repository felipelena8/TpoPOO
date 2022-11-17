package modelo.equipos;

import java.util.ArrayList;
import java.util.List;

import modelo.usuarios.Usuario;

public class AreaSistema {
	private List<Usuario> usuarios;

	public AreaSistema() {
		usuarios = new ArrayList<Usuario>();
	}

	public void agregarUsuario(Usuario user) {
		usuarios.add(user);
	}

}
