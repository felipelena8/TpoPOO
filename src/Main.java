import java.time.LocalDateTime;
import java.util.ArrayList;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Instalacion;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.Turno;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Perfil;
import modelo.enums.Seniority;
import modelo.enums.TipoFactura;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudFactura;
import solicitudes.SolicitudInstalacion;
import solicitudes.SolicitudTecnico;
import vista.PantallaLogin;
import vista.PantallaTecnicos;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();

		controladorSistema.agregarCliente(new SolicitudCliente("Lucas", "Munoz", "19232232", "1158229501","20192322321","lucasmunoz@gmail.com","Calle 412"));
		controladorSistema.agregarCliente(new SolicitudCliente("Felipe", "Costa", "44967716", "1167081366", "204496771602", "felipelena8@gmail.com", "calle 1234"));
		controladorSistema.agregarCliente(new SolicitudCliente("Marcos", "Picon", "44321923", "1167622361", "204432192302", "marcospicon@gmail.com", "por alla 3213"));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin", Seniority.SENIOR));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Pedro", "Gonzales", "2", Perfil.ADMINISTRADOR_SISTEMA, "sistema", "sistema",  Seniority.SENIOR));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Esteban", "Quito", "3", Perfil.OPERADOR, "operador", "operador", Seniority.SENIOR));
		
		
		controladorSistema.crearTecnico(new SolicitudTecnico("Elsa", "Pato", "tecnico", "tecnico","12312", Turno.MANANA, Seniority.SENIOR, Perfil.TECNICO));
		controladorSistema.crearTecnico(new SolicitudTecnico("Elsa", "Cacorchos", "elsaca", "elsaca","1235312", Turno.TARDE, Seniority.SEMISENIOR, Perfil.TECNICO));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin", Seniority.SENIOR));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Juan1", "Perez2", "12", Perfil.ADMINISTRADOR_SISTEMA, "system", "system", Seniority.SENIOR));
		
		controladorSistema.agregarArticulo(DescripcionArticulo.CONDENSADORA, 5);
		controladorSistema.agregarArticulo(DescripcionArticulo.EVAPORADORA, 10);
		controladorSistema.agregarArticulo(DescripcionArticulo.KIT_DE_INSTALACION, 12);
		controladorSistema.agregarArticulo(DescripcionArticulo.SOPORTE_PARED, 6);
		controladorSistema.agregarArticulo(DescripcionArticulo.CONDENSADORA, 4);

		controladorPantalla.mostrarPantallaChica(new PantallaLogin());
	
	}

}
