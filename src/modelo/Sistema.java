package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.equipos.AreaAdministrador;
import modelo.equipos.AreaSistema;
import modelo.equipos.CallCenter;
import modelo.equipos.EquipoTecnico;
import solicitudes.SolicitudUsuario;
import modelo.usuarios.Usuario;

public class Sistema {
	private CallCenter callcenter;
	private EquipoTecnico equipoTecnico;
	private AreaAdministrador areaAdministracion;
	private AreaSistema areaSistema;
	private List<Cliente> clientes;
	private List<Instalacion> instalaciones;
	private List<Articulo> articulos;
	private List<Usuario> usuarios;
	private static Sistema sistema = null;
	
	private Sistema() {
		clientes = new ArrayList<Cliente>();
		instalaciones = new ArrayList<Instalacion>();
		articulos = new ArrayList<Articulo>();
		usuarios = new ArrayList<Usuario>();
	}
	public static Sistema getSistema() {
		if(sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}
	
	public void crearUsuario(Usuario usuario) {
		usuarios.add(usuario);
		switch (usuario.getClass().getSimpleName()) {
		case "Administrador":
			areaAdministracion.agregarUsuario(usuario);
			break;
		case "AdministradorSistema":
			areaSistema.agregarUsuario(usuario);
			break;
		case "Tecnico":
			equipoTecnico.agregarUsuario(usuario);
			break;
		case "Operador":
			callcenter.agregarUsuario(usuario);
			break;
			
		}
		
	}
	
	
	public CallCenter getCallcenter() {
		return callcenter;
	}
	public EquipoTecnico getEquipoTecnico() {
		return equipoTecnico;
	}
	public AreaAdministrador getAreaAdministracion() {
		return areaAdministracion;
	}
	public AreaSistema getAreaSistema() {
		return areaSistema;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}
	public List<Articulo> getArticulos() {
		return articulos;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	
	
	

}
