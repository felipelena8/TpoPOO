import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Turno;
import modelo.enums.Rol;
import modelo.enums.Seniority;
import solicitudes.SolicitudUsuario;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();
		SolicitudUsuario solicitudUsuario =  new SolicitudUsuario("admin", "admin", "admin", "admin", Turno.MANANA, Seniority.SENIOR, Rol.ADMINISTRADOR);
		controladorSistema.crearUsuario(solicitudUsuario);
		System.out.println("Se crea el usuario admin");
		controladorPantalla.mostrarLogin();
		
	}

}
