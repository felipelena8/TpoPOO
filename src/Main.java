import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.enums.Perfil;
import solicitudes.SolicitudEmpleado;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();
		SolicitudEmpleado solicitudEmpleado = new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin"); 
		controladorSistema.agregarEmpleado(solicitudEmpleado);
		controladorPantalla.mostrarPantallaLogin();
		
	}

}
