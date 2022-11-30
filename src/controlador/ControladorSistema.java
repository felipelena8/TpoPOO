package controlador;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import excepciones.FacturaException;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Factura;
import modelo.Instalacion;
import modelo.Item;
import modelo.Sistema;
import modelo.costos.Costo;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Perfil;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudFactura;

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

	public void agregarEmpleado(SolicitudEmpleado solicitud) {
		if (!empleadoExiste(solicitud.getDni())) {
			sistema.agregarEmpleado(new Empleado(solicitud.getNombre(), solicitud.getApellido(), solicitud.getDni(),
					solicitud.getPerfil(), solicitud.getUsername(), solicitud.getPassword()));
			System.out.println("El empleado " + solicitud.getNombre() + " " + solicitud.getApellido()
					+ " ha sido agregado al sistema.");
		}
	}

	public Cliente buscarClientePorCorreo(String correo) {
		for (Cliente cliente : sistema.getClientes()) {
			if (cliente.getCorreoElectronico().equals(correo)) {
				return cliente;
			}
		}
		return null;
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
				}
			}
		}
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
		modelo.setColumnIdentifiers("ID Usuario-Nombre-Apellido-DNI-Usuario-Perfil".split("-"));
		for (Empleado e : sistema.getEmpleados()) {
			if (e.usuarioAsignado()) {
				modelo.addRow(new Object[] { e.getUsuario().getId(), e.getNombre(), e.getApellido(), e.getDni(),
						e.getUsuario().getUsername(), e.getPerfil() });
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

	public boolean emitirFactura(SolicitudFactura soli, String correo) {
		Cliente cliente = buscarClientePorCorreo(correo);
		if (cliente == null) {
			return false;
		}
		Factura f = new Factura(soli.getTipo(), cliente);
		sistema.getAreaAdministracion().emitirFactura(f);
		cliente.agregarFactura(f);
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

}
