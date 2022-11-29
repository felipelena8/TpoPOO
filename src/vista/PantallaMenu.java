package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;

public class PantallaMenu extends JFrame {

	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	JButton btnFacturas, btnUsuarios, btnInstalaciones, btnClientes;

	public PantallaMenu() {
		super("Menu");
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(null);

		btnFacturas = new JButton("Ver facturas");
		Container btnFacturasContainer = new Container();
		btnFacturasContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnFacturasContainer.setBounds(50, 50, 200, 30);
		btnFacturasContainer.add(btnFacturas);

		btnUsuarios = new JButton("Ver usuarios");
		Container btnUsuariosContainer = new Container();
		btnUsuariosContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnUsuariosContainer.setBounds(50, 90, 200, 30);
		btnUsuariosContainer.add(btnUsuarios);

		btnInstalaciones = new JButton("Ver instalaciones");
		Container btnInstalacionesContainer = new Container();
		btnInstalacionesContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnInstalacionesContainer.setBounds(50, 130, 200, 30);
		btnInstalacionesContainer.add(btnInstalaciones);

		btnClientes = new JButton("Ver clientes");
		Container btnClientesContainer = new Container();
		btnClientesContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnClientesContainer.setBounds(50, 170, 200, 30);
		btnClientesContainer.add(btnClientes);

		JFrame pantalla = this;
		btnInstalaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				controladorPantalla.mostrarPantallaGrande(new PantallaInstalaciones());
			}
		});

		btnUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				controladorPantalla.mostrarPantallaGrande(new PantallaUsuarios());
			}
		});

		switch (controladorSistema.getEmpleadoLogueado().getPerfil()) {
		case ADMINISTRADOR:
			btnUsuarios.setEnabled(false);
			btnClientes.setEnabled(false);
			break;
		case ADMINISTRADOR_SISTEMA:
			btnFacturas.setEnabled(false);
			btnUsuarios.setEnabled(false);
			btnInstalaciones.setEnabled(false);
			btnClientes.setEnabled(false);
			break;
		case TECNICO:
			btnFacturas.setEnabled(false);
			btnUsuarios.setEnabled(false);
			btnClientes.setEnabled(false);
			break;
		case OPERADOR:
			btnFacturas.setEnabled(false);
			break;
		}

		panel.add(btnFacturasContainer);
		panel.add(btnUsuariosContainer);
		panel.add(btnInstalacionesContainer);
		panel.add(btnClientesContainer);

		this.add(panel, BorderLayout.CENTER);
	}
}
