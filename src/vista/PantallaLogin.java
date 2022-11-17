package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import controlador.Sistema;

public class PantallaLogin extends JFrame{
	
	private JTextField usernameField, passwordField;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	
	public PantallaLogin(){
		
		super("Iniciar sesión");
		this.setLayout(new BorderLayout());
		
		controladorSistema = ControladorSistema.getInstance(); 
		controladorPantalla = ControladorPantalla.getInstance();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		Container usernameContainer = new Container();
		usernameContainer.setBounds(50, 10, 200, 30);
		usernameContainer.setLayout(new GridLayout(1,2,2,2));
		JLabel usernameLabel = new JLabel("Usuario:");
		usernameField = new JTextField();
		usernameContainer.add(usernameLabel);
		usernameContainer.add(usernameField);
		panel.add(usernameContainer);
		
		Container passwordContainer = new Container();
		passwordContainer.setBounds(50, 50, 200, 30);
		passwordContainer.setLayout(new GridLayout(1,2,2,2));
		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordField = new JPasswordField(8);
		passwordContainer.add(passwordLabel);
		passwordContainer.add(passwordField);
		panel.add(passwordContainer);
		
		
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new GridLayout(1,2,2,2));
		buttonContainer.setBounds(50,200,200,30);
		
		JButton btnAccess = new JButton("Acceder");
		

		class HandlerBtnAceptar implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(controladorSistema.usuarioExiste(usernameField.getText(), passwordField.getText())) {
					System.out.println(usernameField.getText() + " ha iniciado sesión.");
					//cerrarVentana();
					
				}else {
					JOptionPane.showMessageDialog(btnAccess, "Las credenciales ingresadas son incorrectas");
				}
				
			}
		}
		
		HandlerBtnAceptar btnAccessHandler = new HandlerBtnAceptar();
		
		btnAccess.addActionListener(btnAccessHandler);
		
		buttonContainer.add(btnAccess);
		
		panel.add(buttonContainer);
		
		this.add(panel,BorderLayout.CENTER);
		
		
	}
	
	private void cerrarVentana() {
		controladorPantalla.cerrarLogin(this);
	}
	
}