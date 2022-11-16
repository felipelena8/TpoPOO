package equipos;

import java.util.ArrayList;
import java.util.List;

import usuarios.Usuario;

public class EquipoTecnico {
	private List<Usuario> usuarios;

	public EquipoTecnico() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public void agregarUsuario(Usuario user) {
		usuarios.add(user);
	}

}
