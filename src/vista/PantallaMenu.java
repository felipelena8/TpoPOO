package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;

public class PantallaMenu extends JFrame {

	private ControladorPantalla controladorPantalla;
	JButton btnFacturas, btnUsuarios, btnInstalaciones, btnClientes;

	public PantallaMenu() {
		super("Menu");
		controladorPantalla = ControladorPantalla.getInstance();
		Container containerButtons = new Container();
		containerButtons.setLayout(new GridLayout(1, 4));
		btnFacturas = new JButton("Ver facturas");
		btnUsuarios = new JButton("Ver usuarios");
		btnInstalaciones = new JButton("Ver instalaciones");
		btnClientes = new JButton("Ver clientes");
		containerButtons.add(btnUsuarios);
		containerButtons.add(btnClientes);
		containerButtons.add(btnFacturas);
		containerButtons.add(btnInstalaciones);
		this.add(containerButtons);
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
