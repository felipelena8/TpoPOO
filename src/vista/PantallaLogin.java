package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;

public class PantallaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField, passwordField;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaLogin() {

		super("Ingresar al sistema");
		this.setLayout(new BorderLayout());

		controladorSistema = ControladorSistema.getInstance();
		controladorPantalla = ControladorPantalla.getInstance();

		JPanel panel = new JPanel();
		panel.setLayout(null);

		Container usernameContainer = new Container();
		usernameContainer.setBounds(50, 10, 200, 30);
		usernameContainer.setLayout(new GridLayout(1, 2, 2, 2));
		JLabel usernameLabel = new JLabel("Usuario:");
		usernameField = new JTextField();
		usernameContainer.add(usernameLabel);
		usernameContainer.add(usernameField);
		panel.add(usernameContainer);

		Container passwordContainer = new Container();
		passwordContainer.setBounds(50, 50, 200, 30);
		passwordContainer.setLayout(new GridLayout(1, 2, 2, 2));
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(8);
		passwordContainer.add(passwordLabel);
		passwordContainer.add(passwordField);
		panel.add(passwordContainer);

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new GridLayout(1, 2, 2, 2));
		buttonContainer.setBounds(50, 200, 200, 30);

		JButton btnAccess = new JButton("Acceder");

		class HandlerBtnAceptar implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controladorSistema.usuarioExiste(usernameField.getText(), passwordField.getText())) {
					System.out.println("El usuario " + usernameField.getText() + " ha ingresado al sistema.");
					cerrarVentana();
					controladorPantalla.mostrarPantallaChica(new PantallaMenu());

				} else {
					System.out.println("Las credenciales ingresadas son incorrectas");
					JOptionPane.showMessageDialog(btnAccess, "Las credenciales ingresadas son incorrectas");
				}

			}
		}

		btnAccess.addActionListener(new HandlerBtnAceptar());
		btnAccess.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAccess.doClick();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		buttonContainer.add(btnAccess);

		panel.add(buttonContainer);

		this.add(panel, BorderLayout.CENTER);

	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}

}