package controlador;

import javax.swing.JFrame;

import vista.PantallaInstalaciones;
import vista.PantallaLogin;

public class ControladorPantalla {
	
static ControladorPantalla controladorPantalla;

	static	PantallaInstalaciones instalaciones;
	
	static PantallaLogin login;
	
	public static ControladorPantalla getInstance() {
		if(controladorPantalla == null) {
			controladorPantalla = new ControladorPantalla();
			login = new PantallaLogin();
			instalaciones = new PantallaInstalaciones();
			return controladorPantalla;
		}else {
			return controladorPantalla;
		}
	}
	

	public void mostrarPantallaLogin() {
		login.setVisible(true);
		login.setSize(300,300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocation(600, 200);
	}
	
	public void cerrarPantallaLogin() {
		login.dispose();
	}
	
	public void mostrarPantallaInstalaciones() {
		instalaciones.setVisible(true);
		instalaciones.setSize(800, 400);
		instalaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instalaciones.setLocation(400, 200);
	}
	
	public void cerrarPantallaInstalaciones(){
		instalaciones.dispose();
	}
	
}
