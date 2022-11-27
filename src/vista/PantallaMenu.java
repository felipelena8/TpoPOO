package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.ControladorPantalla;

public class PantallaMenu extends JFrame {

	private ControladorPantalla controladorPantalla;
	JButton btnFacturas, btnUsuarios, btnInstalaciones, btnClientes;

	public PantallaMenu() {
		super("Menu");
		controladorPantalla = ControladorPantalla.getInstance();
		this.setLayout(new GridLayout(4, 1));

		btnFacturas = new JButton("Ver facturas");
		btnUsuarios = new JButton("Ver usuarios");
		btnInstalaciones = new JButton("Ver instalaciones");
		btnClientes = new JButton("Ver clientes");

		this.add(btnUsuarios);
		this.add(btnClientes);
		this.add(btnFacturas);
		this.add(btnInstalaciones);

		this.setVisible(true);

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
	}
}
