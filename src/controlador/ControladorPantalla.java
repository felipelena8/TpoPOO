package controlador;

import javax.swing.JFrame;

import vista.PantallaLogin;

public class ControladorPantalla {
	
static ControladorPantalla controladorPantalla;
	
	
	public static ControladorPantalla getInstance() {
		if(controladorPantalla == null) {
			controladorPantalla = new ControladorPantalla();
			return controladorPantalla;
		}else {
			return controladorPantalla;
		}
	}
	

	public void mostrarLogin() {
		PantallaLogin login = new PantallaLogin();
		login.setVisible(true);
		login.setSize(300,300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocation(600, 200);
	}
	
	public void cerrarLogin(PantallaLogin ventana) {
		ventana.dispose();
	}
	
}
