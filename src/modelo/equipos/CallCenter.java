package modelo.equipos;

import java.util.ArrayList;
import java.util.List;

import solicitudes.SolicitudCliente;
import modelo.usuarios.Usuario;

public class CallCenter {
	private List<Usuario> usuarios;

	public CallCenter() {
		usuarios = new ArrayList<Usuario>();
	}

	public void crearCliente(SolicitudCliente solicitud) {

	}

	public void agregarUsuario(Usuario user) {
		usuarios.add(user);
	}

}
