import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.enums.Perfil;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import vista.PantallaLogin;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();
		SolicitudEmpleado solicitudEmpleado = new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin"); 
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Felipe","Costa","44967716",Perfil.ADMINISTRADOR_SISTEMA,"felipelena","lena123"));
		controladorSistema.agregarCliente(new SolicitudCliente("Lucas", "Munoz", "19232232", "1158229501","20192322321","lucasmunoz@gmail.com","Calle 412"));
		controladorSistema.agregarEmpleado(solicitudEmpleado);
		controladorPantalla.mostrarPantallaChica(new PantallaLogin());
		
	}

}
