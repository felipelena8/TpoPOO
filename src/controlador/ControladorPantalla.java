package controlador;

import javax.swing.JFrame;

import vista.PantallaInstalaciones;
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
	
	public void mostrarPantallaGrande(JFrame frame) {
		frame.setVisible(true);
		frame.setSize(800, 400);
		frame.setLocation(400, 200);
	}
	
	public void mostrarPantallaChica(JFrame frame) {
		frame.setVisible(true);
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
	}
	

	public void cerrarPantalla(JFrame frame){
		frame.dispose();
	}
	
}
