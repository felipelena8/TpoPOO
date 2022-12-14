package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.enums.DescripcionArticulo;
import modelo.equipos.AreaAdministrador;
import modelo.equipos.AreaSistema;
import modelo.equipos.CallCenter;
import modelo.equipos.EquipoTecnico;

public class Sistema {
	private CallCenter callcenter;
	private EquipoTecnico equipoTecnico;
	private AreaAdministrador areaAdministracion;
	private AreaSistema areaSistema;
	private List<Cliente> clientes;
	private List<Instalacion> instalaciones;
	private List<Articulo> articulos;
	private List<Empleado> empleados;
	private static Sistema sistema = null;

	private Sistema() {
		clientes = new ArrayList<Cliente>();
		instalaciones = new ArrayList<Instalacion>();
		articulos = new ArrayList<Articulo>();
		empleados = new ArrayList<Empleado>();
		callcenter = new CallCenter(100.0, 200.0, 300.0);
		equipoTecnico = new EquipoTecnico(100.0, 200.0, 300.0);
		areaAdministracion = new AreaAdministrador(100.0, 200.0, 300.0);
		areaSistema = new AreaSistema(100.0, 200.0, 300.0);
	}

	public static Sistema getInstance() {
		if (sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}

	public void agregarEmpleado(Empleado empleado) {
		empleados.add(empleado);
	}

	public void crearUsuario(String dni, Usuario usuario) {
		if (empleados != null) {
			for (Empleado e : empleados) {
				if (dni.equals(e.getDni())) {
					e.asignarUsuario(usuario);
				}
			}
		}
		System.out.println("No hay empleados cargados en el sistema.");
	}

	public Item getItemDetalle(DescripcionArticulo descripcion, int cantidad) {
		if (cantidad > 0) {
			for (Articulo art : articulos) {
				if (art.getDescripcion().equals(descripcion) && art.getStock() >= cantidad) {
					art.quitarStock(cantidad);
					return new Item(art, cantidad);
				}
			}
		}
		return null;
	}
	
	public boolean eliminarEmpleado(Empleado e) {
		for(int i = 0; i< empleados.size(); i++) {
			if(empleados.get(i).equals(e)) {
				empleados.remove(i);
				return true;
			}
		}
		return false;
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarInstalacion(Instalacion instalacion) {
		instalaciones.add(instalacion);
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

	public List<Empleado> getEmpleados() {
		return empleados;
	}

}
