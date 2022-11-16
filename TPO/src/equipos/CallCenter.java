package equipos;

import java.util.ArrayList;
import java.util.List;

import modelo.Factura;
import solicitudes.SolicitudCliente;
import usuarios.Usuario;

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
