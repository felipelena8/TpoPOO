package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Turno;
import modelo.enums.Perfil;
import modelo.enums.Seniority;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudTecnico;

public class PantallaAgregarEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaAgregarEmpleado() {
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3, 1, 1));
		Container contNombre = new Container();
		Container contApellido = new Container();
		Container contDni = new Container();
		Container contUsername = new Container();
		Container contPassword = new Container();
		Container contPerfil = new Container();
		Container contSeniority = new Container();
		Container contTurno = new Container();
		
		contNombre.setLayout(new GridLayout(1, 2, 2, 2));
		contApellido.setLayout(new GridLayout(1, 2, 2, 2));
		contDni.setLayout(new GridLayout(1, 2, 2, 2));
		contUsername.setLayout(new GridLayout(1, 2, 2, 2));
		contPassword.setLayout(new GridLayout(1, 2, 2, 2));
		contPerfil.setLayout(new GridLayout(1, 2, 2, 2));
		contSeniority.setLayout(new GridLayout(1, 2, 2, 2));
		contTurno.setLayout(new GridLayout(1, 2, 2, 2));

		JTextField fieldNombre = new JTextField();
		JTextField fieldApellido = new JTextField();
		JTextField fieldDni = new JTextField();
		JTextField fieldUsername = new JTextField();
		JTextField fieldPassword = new JTextField();
		JComboBox<Perfil> comboPerfil = new JComboBox<Perfil>(Perfil.values());
		JComboBox<Seniority> comboSeniority = new JComboBox<Seniority>(Seniority.values());
		JComboBox<Turno> comboTurno = new JComboBox<Turno>(Turno.values());

		
		JLabel labelNombre = new JLabel("Nombre: ");
		JLabel labelApellido = new JLabel("Apellido: ");
		JLabel labelDni = new JLabel("DNI: ");
		JLabel labelUsername = new JLabel("Usuario: ");
		JLabel labelPassword = new JLabel("Contraseña: ");
		JLabel labelPerfil = new JLabel("Perfil: ");
		JLabel labelSeniority = new JLabel("Seniority: ");
		JLabel labelTurno = new JLabel("Turno: ");

		contNombre.add(labelNombre);
		contNombre.add(fieldNombre);

		contApellido.add(labelApellido);
		contApellido.add(fieldApellido);

		contDni.add(labelDni);
		contDni.add(fieldDni);

		contUsername.add(labelUsername);
		contUsername.add(fieldUsername);

		contPassword.add(labelPassword);
		contPassword.add(fieldPassword);

		contPerfil.add(labelPerfil);
		contPerfil.add(comboPerfil);

		contSeniority.add(labelSeniority);
		contSeniority.add(comboSeniority);
		
		contTurno.add(labelTurno);
		contTurno.add(comboTurno);

		JFrame pantalla = this;
		JButton crearUsuario = new JButton("Crear usuario");
		crearUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = fieldNombre.getText();
				String apellido = fieldApellido.getText();
				String dni = fieldDni.getText();
				Perfil perfil = (Perfil) comboPerfil.getSelectedItem();
				String username = fieldUsername.getText();
				String password = fieldPassword.getText();
				Seniority seniority = (Seniority) comboSeniority.getSelectedItem();
				Turno turno = (Turno) comboTurno.getSelectedItem();
				if (nombre.equals("") || apellido.equals("") || dni.equals("") || username.equals("")
						|| password.equals("")) {
					crearUsuario.setBackground(new Color(255, 0, 0));
					JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
				} else {
					if (perfil.equals(Perfil.TECNICO)) {
						if (controladorSistema.crearTecnico(
								new SolicitudTecnico(nombre, apellido, username, password, dni, turno, seniority,perfil))) {
							JOptionPane.showMessageDialog(null,
									"El empleado " + nombre + " " + apellido + " ha sido agregado al sistema.");
							controladorPantalla.cerrarPantalla(pantalla);
						} else {
							JOptionPane.showMessageDialog(null, "El empleado ya existe");
						}
					} else {
						if (controladorSistema.agregarEmpleado(
								new SolicitudEmpleado(nombre, apellido, dni, perfil, username, password, seniority))) {
							JOptionPane.showMessageDialog(null,
									"El empleado " + nombre + " " + apellido + " ha sido agregado al sistema.");
							controladorPantalla.cerrarPantalla(pantalla);
						} else {
							JOptionPane.showMessageDialog(null, "El empleado ya existe");
						}
					}
				}
			}
		});

		panel.add(contNombre);
		panel.add(contApellido);
		panel.add(contDni);
		panel.add(contUsername);
		panel.add(contPassword);
		panel.add(contPerfil);
		panel.add(contSeniority);
		panel.add(contTurno);
		panel.add(crearUsuario);
		this.add(panel);
	}
}
