package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import solicitudes.SolicitudCliente;

public class PantallaAgregarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaAgregarCliente() {
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 1, 1));
		Container contNombre = new Container();
		Container contApellido = new Container();
		Container contDni = new Container();
		Container contTelefono = new Container();
		Container contCuitCuil = new Container();
		Container contMail = new Container();
		Container contDireccion = new Container();

		contNombre.setLayout(new GridLayout(1, 2, 2, 2));
		contApellido.setLayout(new GridLayout(1, 2, 2, 2));
		contDni.setLayout(new GridLayout(1, 2, 2, 2));
		contTelefono.setLayout(new GridLayout(1, 2, 2, 2));
		contCuitCuil.setLayout(new GridLayout(1, 2, 2, 2));
		contMail.setLayout(new GridLayout(1, 2, 2, 2));
		contDireccion.setLayout(new GridLayout(1, 2, 2, 2));

		JTextField fieldNombre = new JTextField();
		JTextField fieldApellido = new JTextField();
		JTextField fieldDni = new JTextField();
		JTextField fieldTelefono = new JTextField();
		JTextField fieldCuitCuil = new JTextField();
		JTextField fieldMail = new JTextField();
		JTextField fieldDireccion = new JTextField();

		JLabel labelNombre = new JLabel("Nombre: ");
		JLabel labelApellido = new JLabel("Apellido: ");
		JLabel labelDni = new JLabel("DNI: ");
		JLabel labelTelefono = new JLabel("Telefono: ");
		JLabel labelCuitCuil = new JLabel("Cuit/Cuil");
		JLabel labelMail = new JLabel("Mail: ");
		JLabel labelDireccion = new JLabel("Direccion: ");

		contNombre.add(labelNombre);
		contNombre.add(fieldNombre);

		contApellido.add(labelApellido);
		contApellido.add(fieldApellido);

		contDni.add(labelDni);
		contDni.add(fieldDni);

		contTelefono.add(labelTelefono);
		contTelefono.add(fieldTelefono);

		contCuitCuil.add(labelCuitCuil);
		contCuitCuil.add(fieldCuitCuil);

		contMail.add(labelMail);
		contMail.add(fieldMail);

		contDireccion.add(labelDireccion);
		contDireccion.add(fieldDireccion);
		JFrame pantalla = this;
		JButton crearCliente = new JButton("Crear cliente");
		crearCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = fieldNombre.getText();
				String apellido = fieldApellido.getText();
				String dni = fieldDni.getText();
				String telefono = fieldTelefono.getText();
				String cuitCuil = fieldCuitCuil.getText();
				String correoElectronico = fieldMail.getText();
				String direccion = fieldDireccion.getText();

				if (nombre.equals("") || apellido.equals("") || dni.equals("") || telefono.equals("")
						|| cuitCuil.equals("") || correoElectronico.equals("") || direccion.equals("")) {
					crearCliente.setBackground(new Color(255,0,0));
					JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
				}else {
					if(controladorSistema.agregarCliente(new SolicitudCliente(nombre, apellido, dni, telefono, cuitCuil, correoElectronico, direccion))) {
						JOptionPane.showMessageDialog(null, "El cliente se ha agregado correctamente");
						controladorPantalla.cerrarPantalla(pantalla);
					}else {
						JOptionPane.showMessageDialog(null, "El numero de documento ya existe");
					}
				}
			}
		});

		panel.add(contNombre);
		panel.add(contApellido);
		panel.add(contDni);
		panel.add(contTelefono);
		panel.add(contCuitCuil);
		panel.add(contMail);
		panel.add(contDireccion);
		panel.add(crearCliente);
		this.add(panel);
	}
}
