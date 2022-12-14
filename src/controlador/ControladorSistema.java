package controlador;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import excepciones.FacturaException;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Factura;
import modelo.Instalacion;
import modelo.Item;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.costos.Costo;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Perfil;
import modelo.enums.Seniority;
import modelo.equipos.EquipoTecnico;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudFactura;
import solicitudes.SolicitudInstalacion;
import solicitudes.SolicitudTecnico;

public class ControladorSistema {

	static ControladorSistema instance;
	static Sistema sistema;
	static Empleado empleadoLogueado;

	public static ControladorSistema getInstance() {
		sistema = Sistema.getInstance();

		if (instance == null) {
			instance = new ControladorSistema();
			return instance;
		} else {
			return instance;
		}
	}

	public boolean agregarEmpleado(SolicitudEmpleado solicitud) {
		if (!empleadoExiste(solicitud.getDni())) {
			sistema.agregarEmpleado(new Empleado(solicitud.getNombre(), solicitud.getApellido(), solicitud.getDni(),
					solicitud.getPerfil(), solicitud.getUsername(), solicitud.getPassword(), solicitud.getSeniority()));

			return true;
		} else {
			return false;
		}
	}

	public Cliente buscarClientePorDni(String dni) {
		for (Cliente cliente : sistema.getClientes()) {
			if (cliente.getDni().equals(dni)) {
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

	public boolean clienteExiste(String cc) {
		if (sistema.getClientes() != null) {
			for (Cliente c : sistema.getClientes()) {
				if (c.soyCliente(cc)) {
					return true;
				}
			}
		}
		return false;
	}

	public void ingresarUsuario(String username, String password) {
		if (sistema.getEmpleados() != null) {
			for (Empleado e : sistema.getEmpleados()) {
				if (e.getUsuario().validarCredenciales(username, password)) {
					empleadoLogueado = e;
					return;
				}
			}
		}
		empleadoLogueado = null;
	}

	public boolean crearTecnico(SolicitudTecnico tecnico) {
		if (empleadoExiste(tecnico.getDni())) {
			return false;
		}
		Sistema.getInstance().getEquipoTecnico()
				.agregarTecnico(new Tecnico(tecnico.getNombre(), tecnico.getApellido(), tecnico.getDni(),
						tecnico.getRol(), tecnico.getTurno(), tecnico.getUsuario(), tecnico.getPassword(),
						tecnico.getSeniority()));
		return true;
	}

	private boolean empleadoExiste(String dni) {
		if (sistema.getEmpleados() != null) {
			for (Empleado e : sistema.getEmpleados()) {
				if (dni.equals(e.getDni())) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean crearInstalacion(LocalDateTime fInicio, LocalDateTime fFin, String dni) {
		if (fInicio.isBefore(fFin)) {
			Cliente c = buscarClientePorDni(dni);
			sistema.agregarInstalacion(new Instalacion(fInicio, fFin, c));
			return true;
		}
		return false;
	}

	public boolean agregarCliente(SolicitudCliente cliente) {
		if (clienteExiste(cliente.getCuitCuil())) {
			return false;
		}
		sistema.agregarCliente(new Cliente(cliente.getNombre(), cliente.getApellido(), cliente.getDni(),
				cliente.getTelefono(), cliente.getCuitCuil(), cliente.getCorreoElectronico(), cliente.getDireccion()));
		return true;
	}
	
	public boolean eliminarEmpleado(int id) {
		Empleado e = buscarEmpleado(id);
		return Sistema.getInstance().eliminarEmpleado(e);
	}
	
	public Empleado buscarEmpleado(int id) {
		for(Empleado e: Sistema.getInstance().getEmpleados()) {
			if(e.getUsuario().getId() == id) {
				return e;
			}
		}
		return null;
	}

	public DefaultTableModel informacionClientes() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("DNI-Nombre-Apellido-Cuit/Cuil-Telefono-Correo".split("-"));
		for (Cliente c : sistema.getClientes()) {
			modelo.addRow(new Object[] { c.getDni(), c.getNombre(), c.getApellido(), c.getCuitCuil(), c.getTelefono(),
					c.getTelefono() });
		}
		return modelo;
	}

	public DefaultTableModel informacionUsuarios() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("ID Usuario-Nombre-Apellido-DNI-Usuario-Perfil-Seniority".split("-"));
		for (Empleado e : sistema.getEmpleados()) {
			if (e.usuarioAsignado()) {
				modelo.addRow(new Object[] { e.getUsuario().getId(), e.getNombre(), e.getApellido(), e.getDni(),
						e.getUsuario().getUsername(), e.getPerfil(), e.getSeniority() });
			}
		}
		return modelo;
	}

	public DefaultTableModel informacionInstalaciones() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("ID Instalacion-Estado-Fecha inicio-Fecha Fin-Tecnico-Cliente".split("-"));
		for (Instalacion i : sistema.getInstalaciones()) {
			String tecnicoInfo = "Sin asignar";
			if (i.getTecnico() != null) {
				tecnicoInfo = i.getTecnico().getDni();
			}
			modelo.addRow(new Object[] { i.getId(), i.getEstado(), i.getFechaInicio(), i.getFechaFin(), tecnicoInfo,
					i.getCliente().getDni() });

		}
		return modelo;
	}

	public DefaultTableModel informacionFacturas() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelo.setColumnIdentifiers("Numero Factura-Cliente-Tipo de factura-Cantidad de Items-Precio total".split("-"));
		for (Factura f : sistema.getAreaAdministracion().listarFacturas()) {
			modelo.addRow(new Object[] { f.getNumero(), f.getCliente().getDni(), f.getTipo(),
					f.getItemsDetalle().size(), f.calcularTotalFactura() });
		}
		return modelo;
	}

	public DefaultTableModel informacionStock() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("Descripcion-Stock-Precio Unitario".split("-"));
		for (Articulo articulo : sistema.getArticulos()) {
			modelo.addRow(new Object[] { articulo.getDescripcion().name(), articulo.getStock(),
					articulo.getDescripcion().getPrecio() });
		}
		return modelo;
	}

	public DefaultTableModel informacionTecnicos() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("Seniority-Sueldo por Hora".split("-"));
		EquipoTecnico equipoTecnico = sistema.getEquipoTecnico();
		for (Seniority seniority : Seniority.values()) {
			modelo.addRow(new Object[] { seniority.name(), equipoTecnico.obtenerSueldo(seniority) });
		}
		return modelo;
	}

	public static Empleado getEmpleadoLogueado() {
		return empleadoLogueado;
	}

	public boolean agregarItemDetalle(long nroFactura, DescripcionArticulo descr, int cantidad) {
		Factura f = buscarFactura(nroFactura);
		Item item = sistema.getItemDetalle(descr, cantidad);
		if (item == null) {
			return false;
		} else {
			f.agregarItemDetalle(item);
			return true;
		}
	}

	public boolean emitirFactura(SolicitudFactura soli, Instalacion i) {
		if (i.getCliente() == null) {
			return false;
		}
		Factura f = new Factura(soli.getTipo(), i.getCliente());
		for (Item item : i.getArticulos()) {
			f.agregarItemDetalle(item);
		}
		sistema.getAreaAdministracion().emitirFactura(f);

		return true;

	}

	public boolean agregarArticulo(DescripcionArticulo descr, int cantidad) {
		if (cantidad > 0) {
			for (Articulo art : sistema.getArticulos()) {
				if (art.getDescripcion().equals(descr)) {
					art.agregarStock(cantidad);
					return true;
				}
			}
			sistema.getArticulos().add(new Articulo(descr, cantidad));
		}
		return false;
	}

	public Tecnico buscarTecnicoDni(String dni) {
		return Sistema.getInstance().getEquipoTecnico().buscarTecnicoDni(dni);
	}

	public List<Tecnico> tecnicosDisponibles(SolicitudInstalacion soli) {
		return Sistema.getInstance().getEquipoTecnico().obtenerTecnicosDisponibles(soli.getFechaInicio(),
				soli.getFechaFin());
	}

	public void asignarInstalacion(Tecnico tecnico, SolicitudInstalacion solicitudInstalacion) {
		Sistema.getInstance().getEquipoTecnico().asignarInstalacion(tecnico, solicitudInstalacion);
	}

	public static Sistema getSistema() {
		return sistema;
	}

	public Instalacion buscarInstalacion(long id) {
		for (Instalacion i : Sistema.getInstance().getInstalaciones()) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	public void eliminarInstalacion(Instalacion instalacion) {
		List<Instalacion> instalaciones = Sistema.getInstance().getInstalaciones();
		for (int i = 0; i < instalaciones.size(); i++) {
			if (instalaciones.get(i).equals(instalacion)) {
				instalaciones.remove(i);
				JOptionPane.showMessageDialog(null, "La instalacion ha sido eliminada");
				break;

			}
		}
		if (instalacion.getTecnico() != null) {
			instalacion.getTecnico().eliminarInstalacion(instalacion);
		}		
	}

	public void actualizarStock(String nombreArticulo, int nuevoStock, double nuevoPrecio) {
		for (Articulo articulo : ControladorSistema.getSistema().getArticulos()) {
			if (articulo.getDescripcion().name() == nombreArticulo) {
				Sistema.getInstance().getAreaSistema().actualizarStock(articulo, nuevoStock, nuevoPrecio);
			}
		}
	}
	
	public void actualizarSueldoTecnicos(Seniority seniority, double nuevoSueldo) {
		Sistema.getInstance().getAreaSistema().actualizarSueldoTecnicos(seniority, nuevoSueldo);
	}

}
