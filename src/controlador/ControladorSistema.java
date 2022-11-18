package controlador;

import java.util.List;

import excepciones.FacturaException;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Factura;
import modelo.costos.Costo;
import solicitudes.SolicitudEmpleado;

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

	
	public void agregarEmpleado(SolicitudEmpleado solicitud) {
		if(!empleadoExiste(solicitud.getDni())) {
			sistema.agregarEmpleado(new Empleado(solicitud.getNombre(), solicitud.getApellido(), solicitud.getDni(), solicitud.getPerfil(), solicitud.getUsername(), solicitud.getPassword()));
			System.out.println("El empleado " + solicitud.getNombre() + " " + solicitud.getApellido() + " ha sido agregado.");
		}
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
		if(sistema.getEmpleados() != null) {
			for(Empleado e : sistema.getEmpleados()) {
				if(e.getUsuario().getUsername().equals(username) && e.getUsuario().getUsername().equals(password)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	private boolean empleadoExiste(String dni) {
		if(sistema.getEmpleados() != null) {
			for(Empleado e : sistema.getEmpleados()) {
				if(dni.equals(e.getDni())){
					return true;
				}
			}		
		}
		
		return false;
	}

}
