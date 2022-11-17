package controlador;

import java.util.List;

import excepciones.FacturaException;
import modelo.Cliente;
import modelo.Factura;
import modelo.costos.Costo;
import modelo.usuarios.Administrador;
import modelo.usuarios.AdministradorSistema;
import modelo.usuarios.Operador;
import modelo.usuarios.Tecnico;
import modelo.usuarios.Usuario;
import solicitudes.SolicitudUsuario;

public class ControladorSistema {
	
	static ControladorSistema instance;
	
	static Sistema sistema;

	public static ControladorSistema getInstance() {
		sistema = Sistema.getInstance();
		
		if(instance == null) {
			instance = new ControladorSistema();
			return instance;
		}else {
			return instance;
		}
	}

	public boolean crearUsuario(SolicitudUsuario solicitud) {
		if (!usuarioExiste(solicitud.getUsuario(), solicitud.getPassword())) {
			switch (solicitud.getRol()) {
			case ADMINISTRADOR:
				sistema.crearUsuario(new Administrador(solicitud.getNombre(), solicitud.getApellido(),
						solicitud.getUsuario(), solicitud.getPassword()));
				break;
			case ADMINISTRADOR_SISTEMA:
				sistema.crearUsuario(new AdministradorSistema(solicitud.getNombre(),
						solicitud.getApellido(), solicitud.getUsuario(), solicitud.getPassword()));
				break;
			case OPERADOR:
				sistema.crearUsuario(new Operador(solicitud.getNombre(), solicitud.getApellido(),
						solicitud.getUsuario(), solicitud.getPassword()));
				break;
			case TECNICO:
				sistema.crearUsuario(new Tecnico(solicitud.getNombre(), solicitud.getApellido(),
						solicitud.getUsuario(), solicitud.getPassword(), solicitud.getTurno(),
						solicitud.getSeniority()));
				break;
			}
			return true;
		}
		return false;

	}

	public Usuario buscarUsuario(String username, String password) {
		if(sistema.getUsuarios() != null) {
			for (Usuario usuario : sistema.getUsuarios()) {
				if (usuario.getUsuario().equals(username) && usuario.getPassword().equals(password)) {
					return usuario;
				}
			}
			
		}
		return null;
	}
	
	public Cliente buscarCliente(String correo) {
		for(Cliente cliente: sistema.getClientes()) {
			if(cliente.getCorreoElectronico().equals(correo)) {
				return cliente;
			}
		}
		return null;
	}
	public Factura buscarFactura(long id) {
		try {
			return sistema.getAreaAdministracion().buscarFactura(id);
		} catch (FacturaException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Factura> listarFacturas() {
		return sistema.getAreaAdministracion().listarFacturas();
	}
	
	public void ajustarCostoViaje(double costo) {
		Costo.setCostoViaje(costo);
	}
	
	public boolean usuarioExiste(String username, String password) {
		return buscarUsuario(username, password) != null;
	}

}
