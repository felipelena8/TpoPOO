package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.enums.Perfil;

public class PantallaMenu extends JFrame {

	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	JButton btnFacturas, btnUsuarios, btnInstalaciones, btnClientes, btnConfig, btnLogout;

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
		btnFacturasContainer.setBounds(50, 20, 200, 30);
		btnFacturasContainer.add(btnFacturas);

		btnUsuarios = new JButton("Ver usuarios");
		Container btnUsuariosContainer = new Container();
		btnUsuariosContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnUsuariosContainer.setBounds(50, 60, 200, 30);
		btnUsuariosContainer.add(btnUsuarios);

		btnInstalaciones = new JButton("Ver instalaciones");
		Container btnInstalacionesContainer = new Container();
		btnInstalacionesContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnInstalacionesContainer.setBounds(50, 100, 200, 30);
		btnInstalacionesContainer.add(btnInstalaciones);

		btnClientes = new JButton("Ver clientes");
		Container btnClientesContainer = new Container();
		btnClientesContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnClientesContainer.setBounds(50, 140, 200, 30);
		btnClientesContainer.add(btnClientes);

		btnConfig = new JButton("Configuracion sistema");
		Container btnConfigContainer = new Container();
		btnConfigContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnConfigContainer.setBounds(50, 180, 200, 30);
		btnConfigContainer.add(btnConfig);

		btnLogout = new JButton("Logout");
		Container btnLogoutContainer = new Container();
		btnLogoutContainer.setLayout(new GridLayout(1, 2, 2, 2));
		btnLogoutContainer.setBounds(50, 220, 100, 30);
		btnLogoutContainer.add(btnLogout);

		JFrame pantalla = this;

		btnFacturas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				controladorPantalla.mostrarPantallaGrande(new PantallaFacturas());
			}
		});

		btnInstalaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				if(controladorSistema.getEmpleadoLogueado().getPerfil()==Perfil.TECNICO) {
					Tecnico tecnico = Sistema.getInstance().getEquipoTecnico().buscarTecnicoDni(controladorSistema.getEmpleadoLogueado().getDni());
					controladorPantalla.mostrarPantallaGrande(new PantallaInstalacionesTecnico(tecnico));
				}else {
					controladorPantalla.mostrarPantallaGrande(new PantallaInstalaciones());
				}
				
			}
		});

		btnUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				controladorPantalla.mostrarPantallaGrande(new PantallaUsuarios());
			}
		});

		btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.cerrarPantalla(pantalla);
				controladorPantalla.mostrarPantallaGrande(new PantallaConfiguracionSistema());
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaChica(new PantallaLogin());
				cerrarVentana();
			}
		});

		switch (controladorSistema.getEmpleadoLogueado().getPerfil()) {
		case ADMINISTRADOR:
			btnUsuarios.setEnabled(false);
			btnClientes.setEnabled(false);
			btnConfig.setEnabled(false);
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
			btnConfig.setEnabled(false);
			break;
		case OPERADOR:
			btnFacturas.setEnabled(false);
			btnConfig.setEnabled(false);
			break;
		}

		panel.add(btnFacturasContainer);
		panel.add(btnUsuariosContainer);
		panel.add(btnInstalacionesContainer);
		panel.add(btnClientesContainer);
		panel.add(btnConfigContainer);
		panel.add(btnLogoutContainer);

		this.add(panel, BorderLayout.CENTER);
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}
}
